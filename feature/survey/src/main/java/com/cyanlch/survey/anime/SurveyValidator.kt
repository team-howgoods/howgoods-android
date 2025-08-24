package com.cyanlch.survey.anime

object SurveyValidator {
    private const val MIN_ANIME = 1
    private const val MAX_ANIME = 3
    private const val MIN_CHARACTER = 1
    private const val MAX_CHARACTER = 3

    fun validateAnime(form: SurveyForm): ValidationResult {
        val n = form.selectedAnimeIds.size
        return if (n in MIN_ANIME..MAX_ANIME) {
            ValidationResult(true)
        } else {
            ValidationResult(
                false,
                listOf(
                    FieldError(FormFieldKey.AnimeSelection, "애니는 1~3개 선택"),
                ),
            )
        }
    }

    fun validateCharacter(form: SurveyForm): ValidationResult {
        val pool = form.selectedAnimeIds
            .flatMap { form.charactersByAnime[it].orEmpty() }
            .map { it.id }.toSet()

        val chosen = form.selectedCharacterIds
        val errs = buildList {
            if (chosen.size !in MIN_CHARACTER..MAX_CHARACTER) {
                add(
                    FieldError(
                        FormFieldKey.CharacterSelection,
                        "캐릭터는 1~3개 선택",
                    ),
                )
            }
            if (!chosen.all { it in pool }) {
                add(
                    FieldError(
                        FormFieldKey.CharacterSelection,
                        "선택한 애니의 캐릭터만 가능",
                    ),
                )
            }
        }
        return ValidationResult(errs.isEmpty(), errs)
    }

    fun validateGoods(form: SurveyForm): ValidationResult {
        // TODO
        return ValidationResult(true)
    }
}

package com.cyanlch.survey.model

import com.cyanlch.domain.policy.SurveySelectionPolicy.MAX_ANIME
import com.cyanlch.domain.policy.SurveySelectionPolicy.MAX_CHARACTER
import com.cyanlch.domain.policy.SurveySelectionPolicy.MIN_ANIME
import com.cyanlch.domain.policy.SurveySelectionPolicy.MIN_CHARACTER

object SurveyValidator {
    fun validateAnime(form: SurveyForm): ValidationResult {
        val n = form.selectedAnimeIds.size
        return if (n in MIN_ANIME..MAX_ANIME) {
            ValidationResult(true)
        } else {
            ValidationResult(
                false,
                listOf(
                    FieldError(
                        FormFieldKey.AnimeSelection,
                        "애니는 ${MIN_ANIME}~${MAX_ANIME}개 선택",
                    ),
                ),
            )
        }
    }

    fun validateCharacter(form: SurveyForm): ValidationResult {
        val pool = form.selectedAnimeIds
            .flatMap { aid -> form.characterListsByAnime[aid]?.characters.orEmpty() }
            .map { it.id }.toSet()

        val chosen = form.selectedCharacterIds
        val errors = buildList {
            if (chosen.size !in MIN_CHARACTER..MAX_CHARACTER) {
                add(
                    FieldError(
                        FormFieldKey.CharacterSelection,
                        "캐릭터는 ${MIN_CHARACTER}~${MAX_CHARACTER}개 선택",
                    ),
                )
            }
            if (!chosen.all { it in pool }) {
                add(FieldError(FormFieldKey.CharacterSelection, "선택한 애니의 캐릭터만 가능"))
            }
        }
        return ValidationResult(errors.isEmpty(), errors)
    }

    fun validateGoods(form: SurveyForm): ValidationResult {
        val chosen = form.selectedGoodsIds
        val errors = buildList {
            if (chosen.isEmpty()) {
                add(
                    FieldError(
                        FormFieldKey.GoodsSelection,
                        "",
                    ),
                )
            }
        }

        return ValidationResult(errors.isEmpty(), errors)
    }
}

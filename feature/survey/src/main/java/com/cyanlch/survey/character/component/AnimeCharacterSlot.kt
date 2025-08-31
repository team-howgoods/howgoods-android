package com.cyanlch.survey.character.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cyanlch.designsystem.select.HgImageSelector
import com.cyanlch.designsystem.text.HgText
import com.cyanlch.designsystem.ui.HGTypography
import com.cyanlch.domain.model.anime.CharacterId
import com.cyanlch.survey.character.AnimeCharactersGroup

@Composable
fun AnimeCharacterSlot(
    onToggleCharacter: (CharacterId) -> Unit,
    characterGroup: AnimeCharactersGroup,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp),
    ) {
        HgText(
            text = characterGroup.anime.name,
            style = HGTypography.body1SemiBold,
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(characterGroup.items) { character ->
                HgImageSelector(
                    imageUrl = character.imageUrl,
                    caption = character.name,
                    selected = character.isSelected,
                    onClick = { onToggleCharacter(character.id) },
                    modifier = Modifier.size(108.dp),
                )
            }
        }
    }
}

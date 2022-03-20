package com.example.graphalgorithms.feature_node.presentation.screen_edit_add_node.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.graphalgorithms.feature_node.presentation.NodeFeatureViewModel
import com.example.graphalgorithms.feature_node.presentation.screen_edit_add_node.util.AddEditNodeScreenEvent
import com.example.graphalgorithms.feature_node.presentation.ui.theme.lightGray

@ExperimentalComposeUiApi
@Composable
fun WeightTextFiled(viewModel:NodeFeatureViewModel) {
    var valueWeight = viewModel.entitiesOfAddEditScreen.value.weightOfEdge
    val keyboardController = LocalSoftwareKeyboardController.current
    val placeHolderOfEdge = "Weight"
    Box{
        BasicTextField(modifier = Modifier
            .size(72.dp, 36.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .background(lightGray)
            .padding(start = 8.dp),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done, keyboardType = KeyboardType.Number),
            keyboardActions = KeyboardActions(onDone = {keyboardController?.hide()}),
            value = valueWeight,
            onValueChange = {
                viewModel.onAddEditScreenEvent(AddEditNodeScreenEvent.OnWeightChanged(it))
                valueWeight = it
            },
            singleLine = true,
            cursorBrush = SolidColor(MaterialTheme.colors.primary),
            textStyle = LocalTextStyle.current.copy(
                color = MaterialTheme.colors.onSurface,
                fontSize = 14.sp
            ),
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(Modifier.weight(1f)) {
                        if (valueWeight.isEmpty()) Text(
                            placeHolderOfEdge,
                            style = LocalTextStyle.current.copy(
                                color = MaterialTheme.colors.onSurface.copy(alpha = 0.3f),
                                fontSize = 14.sp
                            )
                        )
                        innerTextField()
                    }
                }
            }
        )
    }
}
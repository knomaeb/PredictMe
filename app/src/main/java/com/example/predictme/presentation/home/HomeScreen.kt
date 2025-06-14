package com.example.predictme.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.predictme.presentation.components.ResultDialog
import com.example.predictme.utils.isNameValid

@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    viewModel: HomeViewModel
){

    val textState = remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    var showBottomSheet by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        OutlinedTextField(
            value = textState.value,
            onValueChange = {
                textState.value = it
                isError = textState.value.isNotEmpty() && !isNameValid(textState.value)
                            },
            label = { Text("Enter your name") },
            placeholder = { Text("e.g., John") },
            singleLine = true,
            isError = isError,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                cursorColor = MaterialTheme.colorScheme.primary
            )
        )

        if (isError) {
            Text(
                text = "Name can only contain letters.",
                color = Color.Red,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }

        HorizontalDivider(modifier = Modifier.size(25.dp))

        TextButton(
            modifier = Modifier,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            enabled = textState.value.length > 3 && !isError,
            shape = RoundedCornerShape(8.dp),
            onClick = {
                viewModel.fetchData(textState.value)
                showBottomSheet = true
            }
        ) {
            Text("Predict")
        }

    }

    ResultDialog(
        personName = textState.value
    )


}

@Composable
private fun InputText(text: String) {
    val textState = remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .padding(horizontal = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        OutlinedTextField(
            value = textState.value,
            onValueChange = { textState.value = it },
            label = { Text("Enter your name") },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                cursorColor = MaterialTheme.colorScheme.primary
            )
        )

        HorizontalDivider(modifier = Modifier.size(25.dp))

        TextButton(
            modifier = Modifier,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            shape = RoundedCornerShape(8.dp),
            onClick = {}
        ) {
            Text("Predict")
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun InputTextPreview() {
    InputText("Enter your name")
}
package com.example.predictme.presentation.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.predictme.utils.isNameValid

@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    viewModel: HomeViewModel
){

    val textState = remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    val showDialog = remember { mutableStateOf(false) }
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
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
                showDialog.value = true
            }
        ) {
            Text("Predict")
        }

    }

    if (showDialog.value){

        Dialog(onDismissRequest = { showDialog }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
            ) {

                when (val state = uiState) {

                    is UiState.Loading -> {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator()
                        }
                    }
                    is UiState.Success -> {

                        Log.d("TAG", "ResultDialogSuccess: ${state.data}")

                        Column(
                            modifier = Modifier
                                .wrapContentSize()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceAround
                        ) {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                text =  "Your name is ${textState.value}"
                            )

                            HorizontalDivider(thickness = 1.dp, modifier = Modifier.padding(vertical = 8.dp))

                            Text("Your Age is " + state.data.PersonAge.age.toString())
                            Text("Your Gender is " + state.data.PersonGender.gender.toString())
                            Text("Your Nationality is " + state.data.PersonNationality.country[0].country_id.toString())

                            Spacer(modifier = Modifier.height(16.dp))

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                Button(
                                    modifier = Modifier,
                                    shape = RoundedCornerShape(8.dp),
                                    onClick = {showDialog.value = false}
                                ) {
                                    Text(text = "Close")
                                }
                            }

                        }

                    }

                    is UiState.Error -> {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text(text = state.message, color = Color.Red)
                            Log.d("TAG - ResultDialogError", "ResultDialog: ${state.message}")
                        }
                    }

                }

            }
        }


    }


}
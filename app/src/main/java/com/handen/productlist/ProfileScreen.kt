package com.handen.productlist

import android.content.ActivityNotFoundException
import android.content.Intent
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.hilt.navigation.compose.hiltViewModel
import com.handen.productlist.profile.ProfileViewModel
import com.handen.productlist.profile.ProfileViewModel.AvatarState.Empty
import com.handen.productlist.profile.ProfileViewModel.AvatarState.Taken

const val REQUEST_IMAGE_CAPTURE = 1


private fun dispatchTakePictureIntent(activity: AppCompatActivity) {
    val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    try {
        startActivityForResult(activity, takePictureIntent, REQUEST_IMAGE_CAPTURE, null)
    } catch (e: ActivityNotFoundException) {
        println("Error take photo")
    }
}

@Composable
fun ProfileScreen(viewModel: ProfileViewModel = hiltViewModel()) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) {
        if (it == null) return@rememberLauncherForActivityResult
        viewModel.onPictureTaken(it)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .height(56.dp),
                backgroundColor = Color.White
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Profile",
                        fontSize = 22.sp,
                        color = Color.Black
                    )
                }
            }
        }
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
            val avatarState by viewModel.avatarState.collectAsState()
            val name by viewModel.name.collectAsState()
            val email by viewModel.email.collectAsState()
            Column(
                Modifier.padding(horizontal = 18.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.fillMaxHeight(0.2f))

                if (avatarState is Empty) {
                    Image(
                        modifier = Modifier
                            .background(Color.Gray)
                            .size(128.dp)
                            .padding(16.dp)
                            .clickable { launcher.launch(null) },
                        painter = painterResource(id = R.drawable.ic_add_photo),
                        contentDescription = null,
                    )
                } else {
                    Image(
                        modifier = Modifier
                            .size(128.dp)
                            .clickable { launcher.launch(null) },
                        bitmap = (avatarState as Taken).bitmap.asImageBitmap(),
                        contentDescription = null,
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = name,
                    onValueChange = viewModel::onNameChanged,
                    label = { Text("Name") })
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = email,
                    onValueChange = viewModel::onEmailChanged,
                    label = { Text("Email") })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}

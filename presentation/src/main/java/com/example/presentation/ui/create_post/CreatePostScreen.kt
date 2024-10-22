package com.example.presentation.ui.create_post

import android.media.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.AppTheme
import com.example.domain.features.models.PostModel
import dev.olshevski.navigation.reimagined.hilt.hiltViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePostScreen(
	viewModel : CreatePostViewModel = hiltViewModel(),
) {
	val scrollState = rememberScrollState()
	val postCreationState by viewModel.postCreationState

	val bottomSheetState = rememberModalBottomSheetState()
	val coroutineScope = rememberCoroutineScope()

	var showAddressBottomSheet by remember { mutableStateOf(false) }
	var showCitySelectionSheet by remember { mutableStateOf(false) }

	var address by remember { mutableStateOf(TextFieldValue("Địa chỉ *")) }
	var selectedCity by remember { mutableStateOf("") }

	Scaffold(
		topBar = {
			TopBarClose(onCloseClick = {})
		},
		modifier = Modifier.fillMaxSize()
	) { paddingValues ->

		if (showAddressBottomSheet) {
			ModalBottomSheet(
				onDismissRequest = {
					coroutineScope.launch {
						bottomSheetState.hide()
						showAddressBottomSheet = false
					}
				},
				sheetState = bottomSheetState
			) {
				AddressBottomSheet(
					onCloseClick = {
						coroutineScope.launch {
							bottomSheetState.hide()
							showAddressBottomSheet = false
						}
					},
					onProvinceClick = {
						coroutineScope.launch {
							bottomSheetState.hide()
							showCitySelectionSheet = true
						}
					},
					selectedCity = selectedCity
				)
			}
		}

		// ModalBottomSheet for City selection
		if (showCitySelectionSheet) {
			ModalBottomSheet(
				onDismissRequest = {
					coroutineScope.launch {
						bottomSheetState.hide()
						showCitySelectionSheet = false
					}
				},
				sheetState = bottomSheetState
			) {
				CitySelectionBottomSheet(
					onCloseClick = {
						coroutineScope.launch {
							bottomSheetState.hide()
							showCitySelectionSheet = false
						}
					},
					onCitySelected = { city ->
						selectedCity = city
						coroutineScope.launch {
							bottomSheetState.hide()
							showCitySelectionSheet = false
							showAddressBottomSheet = true // Return to the address sheet
						}
					}
				)
			}
		}


// UI layout
		when (postCreationState) {
			is PostCreationState.Loading -> {
				// Show loading spinner
				CircularProgressIndicator()
			}
			is PostCreationState.Success -> {
				// Show success message
				Text(text = (postCreationState as PostCreationState.Success).message)
			}
			is PostCreationState.Error -> {
				// Show error message
				Text(text = (postCreationState as PostCreationState.Error).message, color = Color.Red)
			}
			else -> {

				Column(
					modifier = Modifier
						.padding(paddingValues)
						.verticalScroll(scrollState)
						.fillMaxSize()
						.padding(16.dp)
				) {

					// Address and Image Section
					Text(
						text = "ĐỊA CHỈ BDS VÀ HÌNH ẢNH",
						style = MaterialTheme.typography.bodyLarge,
						modifier = Modifier.padding(vertical = 8.dp)
					)
					Box(modifier = Modifier
						.fillMaxWidth()
						.background(Color.LightGray, MaterialTheme.shapes.small)
						.padding(12.dp)
						.clickable {
							// Mở bottom sheet khi nhấn vào trường địa chỉ
							coroutineScope.launch {
								bottomSheetState.show()
								showAddressBottomSheet = true
							}
						}) {
						Text(text = address.text)
					}

					Text(
						text = "Xem thêm về Quy định đăng tin.",
						color = Color.Blue,
						style = MaterialTheme.typography.bodySmall
					)
					Spacer(modifier = Modifier.height(8.dp))

					ImageUploadSection()

					// Other Information Section
					Text(
						text = "THÔNG TIN KHÁC",
						style = MaterialTheme.typography.bodyLarge,
						modifier = Modifier.padding(vertical = 8.dp)
					)
					// Interior status Box
					Box(modifier = Modifier
						.fillMaxWidth()
						.background(Color.LightGray, MaterialTheme.shapes.small)
						.padding(12.dp)
						.clickable {
							// Handle interior status field logic
						}) {
						Text(text = "Tình trạng nội thất")
					}

					// Area & Price Section
					Text(
						text = "DIỆN TÍCH & GIÁ",
						style = MaterialTheme.typography.bodyLarge,
						modifier = Modifier.padding(vertical = 8.dp)
					)
					Row(
						modifier = Modifier.fillMaxWidth(),
						horizontalArrangement = Arrangement.SpaceBetween
					) {
						BasicTextField(
							value = TextFieldValue("Diện tích *"),
							onValueChange = {},
							modifier = Modifier
								.weight(1f)
								.padding(end = 8.dp)
								.background(Color.LightGray, MaterialTheme.shapes.small)
								.padding(12.dp)
						)
						BasicTextField(
							value = TextFieldValue("Giá thuê *"),
							onValueChange = {},
							modifier = Modifier
								.weight(1f)
								.padding(start = 8.dp)
								.background(Color.LightGray, MaterialTheme.shapes.small)
								.padding(12.dp)
						)
					}
					BasicTextField(
						value = TextFieldValue("Số tiền cọc"),
						onValueChange = {},
						modifier = Modifier
							.fillMaxWidth()
							.padding(vertical = 8.dp)
							.background(Color.LightGray, MaterialTheme.shapes.small)
							.padding(12.dp)
					)

					// Post Title & Description
					Text(
						text = "TIÊU ĐỀ TIN ĐĂNG VÀ MÔ TẢ CHI TIẾT",
						style = MaterialTheme.typography.bodyLarge,
						modifier = Modifier.padding(vertical = 8.dp)
					)
					BasicTextField(
						value = TextFieldValue("Tiêu đề tin đăng *"),
						onValueChange = {},
						modifier = Modifier
							.fillMaxWidth()
							.background(Color.LightGray, MaterialTheme.shapes.small)
							.padding(12.dp)
					)

					var description by remember { mutableStateOf(TextFieldValue("")) }
					BasicTextField(
						value = description,
						onValueChange = { newValue ->
							if (newValue.text.length <= 70) {
								description = newValue
							}
						},
						modifier = Modifier
							.fillMaxWidth()
							.background(Color.LightGray, MaterialTheme.shapes.small)
							.padding(12.dp)
							.height(100.dp)
					)
					Text(
						text = "${description.text.length}/70",
						style = MaterialTheme.typography.bodySmall,
						modifier = Modifier.align(Alignment.End)
					)
					Text(
						text = "Mô tả chi tiết *",
						color = Color.Red,
						style = MaterialTheme.typography.bodySmall,
						modifier = Modifier.padding(vertical = 8.dp)
					)
					Text(
						text = "Nên có: loại Phòng trọ, vị trí, tiện ích, diện tích, tình trạng nội thất, v.v.",
						style = MaterialTheme.typography.bodySmall,
						color = Color.Gray
					)
					Text(
						text = "Ví dụ: Phòng trọ 30m2 đường Nguyễn Xí, Bình Thạnh, nội thất đầy đủ.",
						style = MaterialTheme.typography.bodySmall,
						color = Color.Gray
					)

					// Submit Button
					Spacer(modifier = Modifier.height(16.dp))
					Button(
						onClick = {
							// Handle post creation
							val post = PostModel(
//								id = "1234",
//								title = "Test Title",
//								price = "Price Test",
//								location = address.text,
//								typeInterior = "Test Interior",
//								acreage = "45",
//								description = "Test description"
							)
							val images = listOf<Image>()
							viewModel.createPost(post, images)
						},
						modifier = Modifier.fillMaxWidth()
					) {
						Text("Đăng tin")
					}
				}
			}
		}
	}
}

@Composable
fun ImageUploadSection() {
	Box(
		modifier = Modifier
			.fillMaxWidth()
			.height(150.dp)
			.border(1.dp, Color.Gray)
			.background(Color.White),
		contentAlignment = Alignment.Center
	) {
		Column(horizontalAlignment = Alignment.CenterHorizontally) {
			Icon(
				imageVector = Icons.Default.DateRange,
				contentDescription = "Upload Image",
				tint = Color(0xFFFFA000),
				modifier = Modifier.size(40.dp)
			)
			Text("Đăng từ 0 đến 12 hình", textAlign = TextAlign.Center, color = Color.Gray)
			Text("Hình Ảnh hợp lệ", color = Color(0xFFFFA000), textAlign = TextAlign.Center)
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarClose(onCloseClick: () -> Unit) {
	TopAppBar(
		title = {
			Text(
				text = "Đăng tin",
				color = Color.White,
				modifier = Modifier.fillMaxWidth(),
				textAlign = TextAlign.Center
			)
		},
		navigationIcon = {},
		actions = {
			IconButton(onClick = { onCloseClick() }) {
				Icon(
					imageVector = Icons.Default.Close,
					contentDescription = "Close",
					tint = Color.White
				)
			}
		},
		modifier = Modifier
			.fillMaxWidth()
			.background(Color.Yellow)
			.height(70.dp)
	)
}


@Composable
fun CitySelectionBottomSheet(onCloseClick: () -> Unit,
	onCitySelected: (String) -> Unit) {
	var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
	val cities = listOf(
		"Tp Hồ Chí Minh", "Hà Nội", "Đà Nẵng", "Cần Thơ", "Bình Dương",
		"An Giang", "Bà Rịa - Vũng Tàu", "Bắc Giang", "Bắc Kạn", "Bạc Liêu",
		"Bắc Ninh", "Bến Tre", "Bình Định", "Bình Phước", "Bình Thuận",
		"Cà Mau", "Cao Bằng", "Đắk Lắk"
	)
	var selectedCity by remember { mutableStateOf("") }

	Column(
		modifier = Modifier
			.fillMaxWidth()
			.padding(16.dp)
	) {
		// Top bar
		Row(
			verticalAlignment = Alignment.CenterVertically,
			modifier = Modifier.fillMaxWidth()
		) {
			IconButton(onClick = onCloseClick) {
				Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
			}
			Spacer(modifier = Modifier.weight(1f))
			Text(
				text = "Chọn tỉnh, thành phố",
				style = MaterialTheme.typography.titleMedium,
				modifier = Modifier.padding(end = 48.dp)
			)
		}

		Spacer(modifier = Modifier.height(16.dp))

		// Search bar
		Row(
			verticalAlignment = Alignment.CenterVertically,
			modifier = Modifier
				.fillMaxWidth()
				.padding(vertical = 8.dp)
				.background(Color.LightGray, MaterialTheme.shapes.small)
				.padding(12.dp)
		) {
			Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
			Spacer(modifier = Modifier.width(8.dp))
			BasicTextField(
				value = searchQuery,
				onValueChange = { searchQuery = it },
				modifier = Modifier.fillMaxWidth()
			)
		}

		// City list
		Column {
			cities.filter { it.contains(searchQuery.text, ignoreCase = true) }.forEach { city ->
				Row(
					verticalAlignment = Alignment.CenterVertically,
					modifier = Modifier
						.fillMaxWidth()
						.padding(vertical = 8.dp)
						.clickable { selectedCity = city
							onCitySelected(city) }
				) {
					Text(
						text = city,
						modifier = Modifier.weight(1f)
					)
					RadioButton(
						selected = selectedCity == city,
						onClick = { selectedCity = city
							onCitySelected(city) },
						colors = RadioButtonDefaults.colors(selectedColor = Color(0xFFFFA000))
					)
				}
			}
		}
	}
}

@Composable
fun DropdownField(value: TextFieldValue, onValueChange: (TextFieldValue) -> Unit
				,onClick : () -> Unit) {
	Box(
		modifier = Modifier
			.fillMaxWidth()
			.padding(vertical = 8.dp)
			.background(Color.LightGray, MaterialTheme.shapes.small)
			.clickable {
				onClick() // Nhận sự kiện click
			}
			.padding(12.dp)
	) {
//		BasicTextField(
//			value = value,
//			onValueChange = onValueChange,
//			modifier = Modifier.fillMaxWidth()
//		)
		Text(text = value.text,
			modifier = Modifier.clickable {
				onClick()
			})
	}
}


@Preview(showBackground = true)
@Composable
fun FiledPreview() {
	AppTheme {
		val viewModel : CreatePostViewModel = hiltViewModel()
		CreatePostScreen(viewModel)
	}
}

//bottom sheet khi nhấn vào trường địa chỉ
@Composable
fun AddressBottomSheet(onCloseClick: () -> Unit,
	onProvinceClick: () -> Unit,
	selectedCity: String) {
	var province by remember { mutableStateOf(TextFieldValue(if (selectedCity.isNotEmpty()) selectedCity else "Chọn tỉnh, thành phố *")) }
	var district by remember { mutableStateOf(TextFieldValue("Chọn quận, huyện, thị xã *")) }
	var ward by remember { mutableStateOf(TextFieldValue("Chọn phường, xã, thị trấn *")) }
	var street by remember { mutableStateOf(TextFieldValue("Tên đường *")) }
	var houseNumber by remember { mutableStateOf(TextFieldValue("")) }
	var showHouseNumber by remember { mutableStateOf(false) }

	Column(
		modifier = Modifier
			.fillMaxWidth()
			.padding(16.dp)
	) {
		// Top bar
		Row(
			verticalAlignment = Alignment.CenterVertically,
			modifier = Modifier.fillMaxWidth()
		) {
			IconButton(onClick = onCloseClick) {
				Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
			}
			Spacer(modifier = Modifier.weight(1f))
			Text(
				text = "Địa chỉ",
				style = MaterialTheme.typography.titleMedium,
				modifier = Modifier.padding(end = 48.dp)
			)
		}

		Spacer(modifier = Modifier.height(16.dp))


		// Dropdown fields
		DropdownField(value = province, onValueChange = { province = it }, onClick = onProvinceClick)
		DropdownField(value = district, onValueChange = { district = it}, onClick = {} )
		DropdownField(value = ward, onValueChange = { ward = it }, onClick = {})
		DropdownField(value = street, onValueChange = { street = it }, onClick = {})

		// House number field
		BasicTextField(
			value = houseNumber,
			onValueChange = { houseNumber = it },
			modifier = Modifier
				.fillMaxWidth()
				.padding(vertical = 8.dp)
				.background(Color.LightGray, MaterialTheme.shapes.small)
				.padding(12.dp)
		)

		// Checkbox
		Row(
			verticalAlignment = Alignment.CenterVertically,
			modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
		) {
			Text(text = "Hiển thị số nhà trong tin rao")
			Spacer(modifier = Modifier.weight(1f))
			Checkbox(
				checked = showHouseNumber,
				onCheckedChange = { showHouseNumber = it }
			)
		}

		// Complete button
//		Button(
//			onClick = onCompleteClick,
//			modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
//			colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
//		) {
//			Text(text = "HOÀN THÀNH", color = Color.White)
//		}
	}
}
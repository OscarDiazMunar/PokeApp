package com.oscar.pokemonapp.presentation.presentation_allpokes.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.oscar.pokemonapp.R
import com.oscar.pokemonapp.commons.Constants
import com.oscar.pokemonapp.presentation.common.CommonScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPokeScreen(
    id: String?,
    viewModel: DetailPokeViewModel,
    navController: NavController
) {

    viewModel.loadDetailPoke(id!!)

    viewModel.getDetailFlow.collectAsState().value.let { state ->
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(R.string.app_name)) },
                    colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Blue)
                )
            }
        ) { innerPadding ->
            CommonScreen(state = state) {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                ){
                        CardDetailPoke(
                            Modifier.align(Alignment.Center),
                            it,
                            innerPadding
                        )
                    }

            }
        }
    }
}

@Composable
fun CardDetailPoke(
    modifier: Modifier,
    detailPokeModel: DetailPokeModel,
    innerPadding: PaddingValues
) {


    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(innerPadding)
    ) {
        item {
            Header(detailPokeModel)
        }

    }
}
@Composable
fun Header(detailPokeModel: DetailPokeModel) {
    val imageurl = Constants.urlImage + detailPokeModel.id + ".png"

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = detailPokeModel.forms[0].name,
            style = MaterialTheme.typography.titleLarge,
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        Card {
            AsyncImage(
                model = imageurl,
                contentDescription = "",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(8.dp)
            )
        }
        Spacer(modifier = Modifier.padding(4.dp))
        Text(

            text = stringResource(R.string.abilities),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier.padding(top = 4.dp)
        )
        Text(

            text = "${detailPokeModel.abilities.map {
                it.ability.name
            }.joinToString(", ")}",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Green,
            modifier = Modifier.padding(top = 4.dp)
        )

        Spacer(modifier = Modifier.padding(4.dp))
        Text(

            text = stringResource(R.string.stats),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier.padding(top = 4.dp)
        )
        Text(
            text = "${detailPokeModel.stats.map {
                it.stat.name + " : " + it.baseStat
            }.joinToString("\n")}",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Gray,
            modifier = Modifier.padding(top = 4.dp)
        )

        Spacer(modifier = Modifier.padding(4.dp))
        Text(

            text = stringResource(R.string.type),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier.padding(top = 4.dp)
        )
        Text(
            text = "${detailPokeModel.types.map {
                it.type.name
            }.joinToString(", ")}",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Gray,
            modifier = Modifier.padding(top = 4.dp)
        )

    }
}
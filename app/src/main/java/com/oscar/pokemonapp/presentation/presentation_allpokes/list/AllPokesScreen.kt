package com.oscar.pokemonapp.presentation.presentation_allpokes.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.oscar.pokemonapp.R
import com.oscar.pokemonapp.commons.Constants
import com.oscar.pokemonapp.commons.NavigationScreen
import com.oscar.pokemonapp.domain.entity.list.GetAllPokesData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllPokesScreen(
    viewModel: ListAllPokesViewModel,
    navController: NavHostController
) {
    val pokesList: LazyPagingItems<GetAllPokesData> = viewModel.getListFlow.collectAsLazyPagingItems()
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = { Text(stringResource(R.string.app_name)) },
                    colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Blue)
                )
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = {
                        searchQuery = it
                        viewModel.setSearchQuery(searchQuery)
                                    },
                    placeholder = { Text(stringResource(R.string.search_poke)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    singleLine = true
                )
            }
        },
                floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(NavigationScreen.Detail.route + "/local" )
                },
                containerColor = Color.Yellow
            ) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "Add Pokémon")
            }
        }
    ) { innerPadding ->
        AllPokes(listPokesModel = pokesList, innerPadding) { itemId ->
            navController.navigate(NavigationScreen.Detail.route + "/" + itemId)
        }
    }
}

@Composable
fun AllPokes(
    listPokesModel: LazyPagingItems<GetAllPokesData>,
    paddingA: PaddingValues,
    onRowClick: (String) -> Unit
) {
    GridPoke(pokes = listPokesModel, paddingA = paddingA, onRowClick)
}

@Composable
fun GridPoke(
    pokes: LazyPagingItems<GetAllPokesData>,
    paddingA: PaddingValues,
    onRowClick: (String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(180.dp),
        modifier = Modifier.padding(paddingA))
    {
        items(pokes.itemCount) { index ->
            val poke = pokes[index] ?: return@items
            PokeImage(modifier = Modifier, poke){
                onRowClick(it)
            }
        }
    }
}

@Composable
fun PokeImage(
    modifier: Modifier,
    poke: GetAllPokesData,
    onRowClick: (String) -> Unit
) {
    val imageurl = Constants.urlImage + poke.id + ".png"
    Column(
        modifier = Modifier
            .padding(10.dp)
            .clickable { onRowClick(poke.id) }
    ) {
        Card {
            AsyncImage(
                model = imageurl,
                contentDescription = "",
                modifier = Modifier.padding(5.dp)
            )
        }
        Box {
            Text(text = poke.name)
        }
    }
}

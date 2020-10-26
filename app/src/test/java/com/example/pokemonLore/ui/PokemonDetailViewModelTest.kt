package com.example.pokemonLore.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.pokemonLore.api.Response
import com.example.pokemonLore.entities.Pokemon
import com.example.pokemonLore.entities.PokemonSprite
import com.example.pokemonLore.repositories.PokemonRepository
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class PokemonDetailViewModelTest {

    @Mock
    private lateinit var pokemonRepository: PokemonRepository

    @Mock
    private lateinit var pokemonObserver: Observer<Response<Pokemon>>

    private lateinit var pokemonDetailViewModel: PokemonDetailViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        MockitoAnnotations.initMocks(this)
        pokemonDetailViewModel = PokemonDetailViewModel(pokemonRepository)
    }

    @Test
    fun `pokemon detail view model getPokemonById is success`() {
        //arrange
        val pokemonLiveData = pokemonDetailViewModel.pokemonLiveData
        val pokemonSprite = PokemonSprite("front")
        val pokemon = Pokemon("name",  pokemonSprite)

        `when`(pokemonRepository.getPokemonById("1")).thenReturn(
            Single.just(pokemon)
        )

        //act
        pokemonDetailViewModel.getPokemonById("1")

        //assert
        assertEquals(pokemon.name, (pokemonLiveData.value as Response.Success).data.name)
        assertEquals(pokemon.sprite.frontDefault, (pokemonLiveData.value as Response.Success).data.sprite.frontDefault)
    }

    @Test
    fun `pokemon detail view model getPokemonById is error`() {
        //arrange
        val pokemonLiveData = pokemonDetailViewModel.pokemonLiveData
        val exception = Exception()

        `when`(pokemonRepository.getPokemonById("1")).thenReturn(
            Single.error(exception)
        )

        pokemonDetailViewModel.pokemonLiveData.observeForever(pokemonObserver)

        //act
        pokemonDetailViewModel.getPokemonById("1")

        //assert
        verify(pokemonObserver).onChanged(Response.Error(exception))
    }

}
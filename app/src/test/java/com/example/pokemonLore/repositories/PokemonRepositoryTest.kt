package com.example.pokemonLore.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.pokemonLore.api.PokemonLoreApiManager
import com.example.pokemonLore.entities.Pokemon
import com.example.pokemonLore.models.PokemonResponse
import com.example.pokemonLore.models.PokemonSpriteResponse
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.observers.TestObserver
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class PokemonRepositoryTest {

    @Mock
    private lateinit var pokemonLoreApiManager: PokemonLoreApiManager

    private lateinit var pokemonRepository: PokemonRepository

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }

        MockitoAnnotations.initMocks(this)

        pokemonRepository = PokemonRepository(pokemonLoreApiManager)
    }

    @Test
    fun `pokemon repository getPokemonById get Pokemon is success`() {
        //arrange
        val pokemonSpriteResponse = PokemonSpriteResponse("frontImg")
        val pokemonResponse = PokemonResponse("pokemonName", pokemonSpriteResponse)
        `when`(pokemonLoreApiManager.getPokemonById("1")).thenReturn(
            Single.just(pokemonResponse)
        )

        //act
        val pokemon = pokemonRepository.getPokemonById("1").blockingGet()

        //assert
        assertEquals("pokemonName", pokemon.name)
        assertEquals("frontImg", pokemon.sprite.frontDefault)
    }

    @Test
    fun `pokemon repository getPokemonById get Pokemon is error`() {
        //arrange
        val exception = Exception()
        `when`(pokemonLoreApiManager.getPokemonById("1")).thenReturn(
            Single.error(exception)
        )

        val subscriber = TestObserver<Pokemon>()

        //act
        pokemonRepository.getPokemonById("1").subscribe(subscriber)

        //assert
        subscriber.assertError(exception)
    }
}
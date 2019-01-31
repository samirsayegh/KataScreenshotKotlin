package com.karumi.ui.view

import android.os.Bundle
import com.github.salomonbrys.kodein.Kodein.Module
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.karumi.data.repository.SuperHeroRepository
import com.karumi.domain.model.SuperHero
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Test
import org.mockito.Mock

class SuperHeroesDetailActivityTest : AcceptanceTest<SuperHeroDetailActivity>(SuperHeroDetailActivity::class.java) {

    @Mock
    private lateinit var repository: SuperHeroRepository

    @Test
    fun verifyAvengerSuperHero() {
        whenever(repository.getByName(NAME)).thenReturn(superHero())

        val activity = startActivity(Bundle().apply { putString(SuperHeroDetailActivity.SUPER_HERO_NAME_KEY, NAME) })

        compareScreenshot(activity)
    }

    @Test
    fun verifyNonAvengerSuperHero() {
        whenever(repository.getByName(NAME)).thenReturn(superHero(isAvenger = false))

        val activity = startActivity(Bundle().apply { putString(SuperHeroDetailActivity.SUPER_HERO_NAME_KEY, NAME) })

        compareScreenshot(activity)
    }

    @Test
    fun verifyLongNameAndDescriptionSuperHero() {
        val name = "This is a super long name for a hero that doesn't deserve to have it. I recommend for the next time to use an empty name or a single char name"
        val description = "The description for the super hero " +
                "This is a super long name for a hero that doesn't deserve to have it. I recommend for the next time to use an empty name or a single char name" +
                " is that he likes to be known for spam a lot and spam a lot and spam a lot and spam a lot and spam a lot and spam a lot and spam a lot and spam a lot and spam a lot and spam a lot and spam a lot and spam a lot and spam a lot and spam a lot and spam a lot and spam a lot and spam a lot and spam a lot and spam a lot and spam a lot and spam a lot and spam a lot and spam a lot and spam a lot and spam a lot and spam a lot and spam a lot and spam a lot and spam a lot and spam a lot and spam a lot and spam a lot and spam a lot and spam a lot and spam a lot and spam a lot and spam a lot and spam a lot and spam a lot and spam a lot and spam a lot and spam a lot and spam a lot and spam a lot and spam a lot and spam a lot and spam a lot and spam a lot and spam a lot and spam a lot and "
        whenever(repository.getByName(name)).thenReturn(superHero(isAvenger = false, description = description))

        val activity = startActivity(Bundle().apply { putString(SuperHeroDetailActivity.SUPER_HERO_NAME_KEY, name) })

        compareScreenshot(activity)
    }

    @Test
    fun verifySingleCharNameAndDescriptionSuperHero() {
        val name = "?"
        val description = "/"
        whenever(repository.getByName(name)).thenReturn(superHero(isAvenger = false, description = description))

        val activity = startActivity(Bundle().apply { putString(SuperHeroDetailActivity.SUPER_HERO_NAME_KEY, name) })

        compareScreenshot(activity)
    }

    @Test
    fun verifyEmptyNameAndDescriptionSuperHero() {
        val name = ""
        val description = ""
        whenever(repository.getByName(name)).thenReturn(superHero(isAvenger = false, description = description))

        val activity = startActivity(Bundle().apply { putString(SuperHeroDetailActivity.SUPER_HERO_NAME_KEY, name) })

        compareScreenshot(activity)
    }

    private fun superHero(
        name: String = NAME,
        description: String = DESCRIPTION,
        isAvenger: Boolean = IS_AVENGER,
        photo: String = PHOTO
    ) = SuperHero(name, photo, isAvenger, description)

    override val testDependencies = Module(allowSilentOverride = true) {
        bind<SuperHeroRepository>() with instance(repository)
    }

    companion object {
        private const val NAME = "name"
        private const val DESCRIPTION = "description"
        private const val IS_AVENGER = true
        private const val PHOTO = "photo"
    }
}

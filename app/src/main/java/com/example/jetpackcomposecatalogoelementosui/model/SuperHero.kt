package com.example.jetpackcomposecatalogoelementosui.model

import androidx.annotation.DrawableRes

data class SuperHero(
    var idSuperHero: Int,
    var superHeroName: String,
    var realName: String,
    var publisher: String,
    @DrawableRes var photo: Int
)

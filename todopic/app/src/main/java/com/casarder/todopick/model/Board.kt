package com.casarder.todopick.model

/**
 * Created by fabiolourenco on 14/04/18.
 */
class Board(val name: String, val desc: String, val id: String, val prefs: Prefs)
class Prefs(val backgroundColor: String, val backgroundImageScaled: Array<BackgroundImageScaled>)
class BackgroundImageScaled(val witdh: Float, val height: Float, val url: String)

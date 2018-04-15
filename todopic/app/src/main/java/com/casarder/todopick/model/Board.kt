package com.casarder.todopick.model

import kotlin.collections.List

/**
 * Created by fabiolourenco on 14/04/18.
 */
class Board(val name: String, val desc: String, val id: String, val prefs: Prefs, val lists: List<com.casarder.todopick.model.List>)
class Prefs(val backgroundColor: String, val backgroundImageScaled: List<BackgroundImageScaled>?)
class BackgroundImageScaled(val witdh: Float, val height: Float, val url: String)
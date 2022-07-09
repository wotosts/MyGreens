package com.wotosts.mygreens.model

import java.util.Date

/**
 * @property id db 저장용
 * @property name 식물 이름
 * @property image 등록시 첨부된 식물 사진
 * @property description 식물 설명(기타, 특이사항)
 * @property addedDate 식물 등록일
 * @property deathDate 식물 죽은날
 * @property waterLevel 물 양
 * @property solarLevel 햇빛 양
 * @property waterPeriod 물 주는 주기(일)
 * @property nutriPeriod 비료 주는 주기(일)
 */
data class Plant(
    val id: Long,
    val name: String,
    val image: String = "",
    val description: String = "",
    val addedDate: Date = Date(),
    val firstDate: Date = Date(),
    val deathDate: Date? = null,
    val waterLevel: WaterLevel = WaterLevel.MID,
    val solarLevel: SolarLevel = SolarLevel.MID,
    val nutriPeriod: Int = 0,
    val waterPeriod: Int = 0
)

enum class WaterLevel {
    LOW, MID, HIGH
}

enum class SolarLevel {
    LOW, MID, HIGH
}

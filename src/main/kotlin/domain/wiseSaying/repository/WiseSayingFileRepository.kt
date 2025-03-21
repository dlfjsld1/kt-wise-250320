package com.think.domain.wiseSaying.repository

import com.think.domain.wiseSaying.entity.WiseSaying
import com.think.global.AppConfig
import java.nio.file.Path

class WiseSayingFileRepository : WiseSayingRepository {

    private var lastId: Int = 0

    init {
        initTable()
    }

    val tableDirPath: Path
        get() = AppConfig.tableDirPath.resolve("wiseSaying")

    override fun save(wiseSaying: WiseSaying): WiseSaying {
        if (wiseSaying.isNew()) {
            val new = wiseSaying.copy(id = ++lastId)
            saveOnDisk(new)
            return new
        }

        saveOnDisk(wiseSaying)
        return wiseSaying
    }

    private fun saveOnDisk(wiseSaying: WiseSaying) {
        tableDirPath.resolve("${wiseSaying.id}.json").toFile().writeText(wiseSaying.jsonStr)
    }

    override fun findAll(): List<WiseSaying> {
        return listOf()
    }

    override fun findById(id: Int): WiseSaying? {
        return null
    }

    override fun delete(wiseSaying: WiseSaying) {

    }

    override fun clear() {

    }

    fun initTable() {
        tableDirPath.toFile().run {
            if(!exists()) {
                mkdirs()
            }
        }
    }
}
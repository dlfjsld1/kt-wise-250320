package com.think.domain.wiseSaying.repository

import com.think.domain.wiseSaying.entity.WiseSaying
import com.think.global.AppConfig
import java.nio.file.Path

class WiseSayingFileRepository : WiseSayingRepository {

    init {
        initTable()
    }

    val tableDirPath: Path
        get() = AppConfig.tableDirPath.resolve("wiseSaying")

    override fun save(wiseSaying: WiseSaying): WiseSaying {
        val target = if (wiseSaying.isNew()) wiseSaying.copy(id = genNextId())
        else wiseSaying

        return target.also {
            saveOnDisk(it)
        }
    }

    private fun saveOnDisk(wiseSaying: WiseSaying) {
        tableDirPath.resolve("${wiseSaying.id}.json").toFile().writeText(wiseSaying.jsonStr)
    }

    override fun findAll(): List<WiseSaying> {
        return tableDirPath.toFile()
            .listFiles()
            ?.filter { it.extension == "json" }
            ?.map { WiseSaying.fromJson(it.readText()) }
            .orEmpty()

    }

    override fun findById(id: Int): WiseSaying? {
        //.resolve는 폴더를 리턴함. toFile()으로 파일로 변환
        return tableDirPath.resolve("${id}.json").toFile()
            .takeIf{ it.exists() }
            ?.let {
                WiseSaying.fromJson(it.readText())
            }
    }

    override fun delete(wiseSaying: WiseSaying) {
        tableDirPath.resolve("${wiseSaying.id}.json").toFile().delete()
    }

    override fun clear() {
        //파일 폴더 전부 재귀적으로 삭제
        tableDirPath.toFile().deleteRecursively()
    }

    fun saveLastId(id: Int) {
        tableDirPath.resolve("lastId.txt").toFile().writeText(id.toString())
    }

    fun loadLastId(): Int {
        tableDirPath.resolve("lastId.txt").toFile().run {
            if (!exists()) {
                return 0
            }
            return readText().toInt()
        }

    }

    private fun genNextId(): Int {
        return loadLastId().also {
            saveLastId(it + 1)
        }
    }

    fun initTable() {
        tableDirPath.toFile().run {
            if(!exists()) {
                mkdirs()
            }
        }
    }
}
package com.think.domain.wiseSaying.service

import com.think.domain.wiseSaying.entity.WiseSaying
import com.think.global.SingletonScope
import com.think.standard.Page

class WiseSayingService {
    private val wiseSayingRepository = SingletonScope.wiseSayingRepository

    fun write(saying: String, author: String): WiseSaying {
        return wiseSayingRepository.save(WiseSaying(saying = saying, author = author))
    }

    fun getitems(): List<WiseSaying> {
        return wiseSayingRepository.findAll()

    }

    fun delete(wiseSaying: WiseSaying) {
        wiseSayingRepository.delete(wiseSaying)
    }

    fun getItem(id: Int): WiseSaying? {
        return wiseSayingRepository.findById(id)
    }

    fun modify(wiseSaying: WiseSaying, saying: String, author: String): WiseSaying? {

        return wiseSayingRepository.save(wiseSaying.copy(saying = saying, author = author))

    }

    fun build() {
        wiseSayingRepository.build()
    }

    fun findByKeyword(keywordType: String, keyword: String): List<WiseSaying> {
        return when(keywordType) {
            "author" -> return wiseSayingRepository.findByAuthorLike(keyword)
            else -> return wiseSayingRepository.findBySayingLike(keyword)
        }
    }

    fun findByKeywordPaged(keywordType: String, keyword: String, page: Int, pageSize: Int): Page {
        return when (keywordType) {
            "author" -> wiseSayingRepository.findByAuthorLikePaged(keyword, page, pageSize)
            else -> wiseSayingRepository.findBySayingLikePaged(keyword, page, pageSize)
        }
    }
}
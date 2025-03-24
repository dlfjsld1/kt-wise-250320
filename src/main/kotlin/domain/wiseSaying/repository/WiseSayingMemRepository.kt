package com.think.domain.wiseSaying.repository

import com.think.domain.wiseSaying.entity.WiseSaying

class WiseSayingMemRepository: WiseSayingRepository {
    var lastId: Int = 0
    val wiseSayings = mutableListOf<WiseSaying>()

    override fun save(wiseSaying: WiseSaying): WiseSaying {

        if(wiseSaying.isNew()) {
            val new = wiseSaying.copy(id = ++lastId)
            wiseSayings.add(new)
            return new
        }

        //id가 동일한 첫 번째 인덱스를 찾는다
        wiseSayings.indexOfFirst { it.id == wiseSaying.id }.let {
            wiseSayings[it] = wiseSaying
        }
        return wiseSaying
    }

    override fun findAll(): List<WiseSaying> {
        //불변으로 변경해 조회되도록 수정
        return wiseSayings.toList()
    }

    override fun delete(wiseSaying: WiseSaying) {
        wiseSayings.remove(wiseSaying)
    }

    override fun findById(id: Int): WiseSaying? {
        return wiseSayings.find { it.id == id }
    }

    override fun clear() {
        wiseSayings.clear()
        lastId = 0
    }

    override fun findByAuthorLike(keyword: String): List<WiseSaying> {
        TODO("Not yet implemented")
    }

    override fun findBySayingLike(keyword: String): List<WiseSaying> {
        TODO("Not yet implemented")
    }
}
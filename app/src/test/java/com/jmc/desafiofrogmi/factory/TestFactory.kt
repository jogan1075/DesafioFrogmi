package com.jmc.desafiofrogmi.factory

import com.jmc.desafiofrogmi.data.remote.model.Attributes
import com.jmc.desafiofrogmi.data.remote.model.Brands
import com.jmc.desafiofrogmi.data.remote.model.Coordinates
import com.jmc.desafiofrogmi.data.remote.model.Data
import com.jmc.desafiofrogmi.data.remote.model.DataX
import com.jmc.desafiofrogmi.data.remote.model.Links
import com.jmc.desafiofrogmi.data.remote.model.LinksX
import com.jmc.desafiofrogmi.data.remote.model.Meta
import com.jmc.desafiofrogmi.data.remote.model.Pagination
import com.jmc.desafiofrogmi.data.remote.model.Relationships
import com.jmc.desafiofrogmi.data.remote.model.StoreResponse
import com.jmc.desafiofrogmi.data.remote.model.Zones
import com.jmc.desafiofrogmi.utils.testing.RandomFactory

object TestFactory {

    fun makeStoreResponse(): StoreResponse {
        return StoreResponse(
            links = makeLinksX(),
            meta = makeMeta(),
            data = makeListData(3)
        )
    }

    fun makeListData(count: Int) =
        ArrayList((0..count).map { makeData() })

    private fun makeData() = Data(
        id = RandomFactory.generateString(),
        type = RandomFactory.generateString(),
        attributes = Attributes(
            active = RandomFactory.generateBoolean(),
            code = RandomFactory.generateString(),
            coordinates = Coordinates(
                latitude = RandomFactory.generateDouble(),
                longitude = RandomFactory.generateDouble()
            ),
            name = RandomFactory.generateString(),
            createdAt = RandomFactory.generateString(),
            fullAddress = RandomFactory.generateString()
        ),
        links = Links(self = RandomFactory.generateString()),
        relationships = Relationships(
            brands = Brands(
                data = DataX(
                    id = RandomFactory.generateString(),
                    type = RandomFactory.generateString()
                )
            ),
            zones = Zones(
                data = DataX(
                    id = RandomFactory.generateString(),
                    type = RandomFactory.generateString()
                )
            )
        )
    )


    private fun makeLinksX() = LinksX(
        first = RandomFactory.generateString(),
        last = RandomFactory.generateString(),
        next = RandomFactory.generateString(),
        prev = RandomFactory.generateString(),
        self = RandomFactory.generateString()
    )


    private fun makeMeta() = Meta(
        pagination = makePagination(),
    )

    private fun makePagination() = Pagination(
        perPage = RandomFactory.generateInt(),
        currentPage = RandomFactory.generateInt(),
        total = RandomFactory.generateInt()
    )
}
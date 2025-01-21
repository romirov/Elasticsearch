package com.marulab.elk.repository

import com.marulab.elk.dto.Message
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ElasticRepo: ElasticsearchRepository<Message, UUID>
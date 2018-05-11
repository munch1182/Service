package com.munch.service.controller

import com.munch.service.base.BaseResBean
import com.munch.service.base.ResNotFoundDataBean
import com.munch.service.base.ResUnknownBean
import com.munch.service.dao.IPDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileOutputStream
import javax.annotation.Resource
import javax.servlet.http.HttpServletRequest

@RestController
class PostController {

    @Autowired
    lateinit var req: HttpServletRequest

    @Resource
    lateinit var ipDao: IPDao

    companion object {
        private const val WAR_NAME = "api.war"

        private val listType = List(2, { i ->
            when (i) {
                0 -> "API"
                else -> "OTHER"
            }
        })

        private val listPath = List(2, { i ->
            when (i) {
                0 -> "C:/Workspace/website/api/"
                else -> "C:/Workspace/other/"
            }
        })

        fun uploadFile(file: ByteArray, filePath: String, fileName: String) {
            val dir = File(filePath)
            if (!dir.exists()) {
                dir.mkdirs()
            }
            var out: FileOutputStream? = null
            try {
                out = FileOutputStream(filePath + fileName)
                out.write(file)
                out.flush()
            } catch (e: Exception) {
                throw e
            } finally {
                out?.close()
            }
        }
    }

    @RequestMapping("/upload")
    fun post(@RequestParam(name = "file") file: MultipartFile,
             @RequestParam(name = "type") type: String): BaseResBean<String> {

        val index = listType.indexOf(type)
        if (index != -1) {
            val fileName = if (index == 0) WAR_NAME else file.originalFilename ?: "other"
            try {
                uploadFile(file.bytes, listPath[index], fileName)
            } catch (e: Exception) {
                return ResUnknownBean(e.localizedMessage)
            }
        }
        return ResNotFoundDataBean("type")

    }

}
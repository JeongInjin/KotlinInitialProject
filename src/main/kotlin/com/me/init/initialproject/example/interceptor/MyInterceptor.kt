package com.me.init.initialproject.example.interceptor

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import mu.KLogging
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor

@Component
class MyInterceptor : HandlerInterceptor {
    companion object : KLogging()

    private val REQUESTED_TIME = "requestedTime"
    private val REQUEST_ORIGIN_URI = "requestOriginUri"

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        logger.info { "preHandle 호출" }
        requestSet(request)

        return super.preHandle(request, response, handler)
    }

    override fun afterCompletion(
        request: HttpServletRequest, response: HttpServletResponse, handler: Any, ex: Exception?
                                ) {
        logger.info { "afterCompletion 호출" }
        val requestedTime = request.getAttribute(REQUESTED_TIME) as Long
        val responseTime = System.currentTimeMillis()


        super.afterCompletion(request, response, handler, ex)
    }

    private fun requestSet(request: HttpServletRequest) {
        val requestedTime = System.currentTimeMillis()
        request.setAttribute(REQUESTED_TIME, requestedTime)
        request.setAttribute(REQUEST_ORIGIN_URI, request.requestURI)
    }
}
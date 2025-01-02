package nl.jaysh.brugnl.core.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpRequest
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.ClientHttpResponse
import org.springframework.web.client.RestTemplate
import java.io.IOException

@Configuration
class RestTemplateConfig {

    @Bean
    fun restTemplate(): RestTemplate {
        val restTemplate = RestTemplate()
        restTemplate.interceptors.add(ContentTypeInterceptor)
        return restTemplate
    }

    private companion object ContentTypeInterceptor : ClientHttpRequestInterceptor {
        @Throws(IOException::class)
        override fun intercept(
            request: HttpRequest,
            body: ByteArray,
            execution: ClientHttpRequestExecution,
        ): ClientHttpResponse {
            request.headers.add(HttpHeaders.CONTENT_TYPE, "application/json")
            return execution.execute(request, body)
        }
    }
}
/*
 *    Copyright (c) 2023 Udhayarajan M
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 */

import com.mugames.vidsnapkit.dataholders.Error
import com.mugames.vidsnapkit.dataholders.Result
import com.mugames.vidsnapkit.extractor.Extractor
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertFails

/**
 * @author Udhaya
 * Created on 01-01-2023
 */

class DemoTest {

    @Test
    fun mainTest() {
        var url = "https://www.instagram.com/p/Cn7ewxmyHeX/?igshid=NDdhMjNiZDg="
        url = "https://twitter.com/NaguibSawiris/status/1622112587805593601?s=20&t=qYf-wd9fR2ICdebHDgFdbw"
        url = "https://fb.watch/ivvyC8pHKW/"
//        url = "https://vimeo.com/771088289"
        val extractor = Extractor.findExtractor(url)
        runBlocking {
            extractor?.apply {
//                cookies="APPROPRIATE COOKIES"
                start {
                    when (it) {
                        is Result.Failed -> {
                            println((it.error as Error.InternalError).e)
                            assertFails {
                                it.error
                            }
                        }

                        is Result.Success -> {
                            it.formats.forEach {
                                it.videoData.forEach {
                                    println(it)
                                }
                                it.audioData.forEach {
                                    println(it)
                                }
                                it.imageData.forEach {
                                    println(it)
                                }
                            }
                        }

                        else -> println(it)
                    }
                }
            }
        }
    }
}


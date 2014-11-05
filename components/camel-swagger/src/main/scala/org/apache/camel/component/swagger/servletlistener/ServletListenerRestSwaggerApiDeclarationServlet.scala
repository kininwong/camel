/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.component.swagger.servletlistener

import javax.servlet.ServletConfig

import com.wordnik.swagger.core.SwaggerContext

import org.apache.camel.component.swagger.RestSwaggerApiDeclarationServlet
import org.apache.camel.CamelContext

/**
 * To lookup CamelContext from the <a href="http://camel.apache.org/servletlistener-component.html">servletlistener</a> component.
 */
class ServletListenerRestSwaggerApiDeclarationServlet extends RestSwaggerApiDeclarationServlet {

  override def lookupCamelContext(config: ServletConfig): CamelContext = {
    val obj = config.getServletContext.getAttribute("CamelContext")
    if (obj != null) {
      camel = obj.asInstanceOf[CamelContext]
      SwaggerContext.registerClassLoader(camel.getApplicationContextClassLoader)
    }
    camel
  }

}

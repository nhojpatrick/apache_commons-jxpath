/*
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowlegement:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowlegement may appear in the software itself,
 *    if and wherever such third-party acknowlegements normally appear.
 *
 * 4. The names "The Jakarta Project", "Tomcat", and "Apache Software
 *    Foundation" must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache"
 *    nor may "Apache" appear in their names without prior written
 *    permission of the Apache Group.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

package org.apache.commons.jxpath.servlet;

import org.apache.commons.jxpath.*;
import javax.servlet.jsp.*;
import java.util.*;

/**
 * Implementation of the DynamicPropertyHandler interface that provides
 * access to attributes of a PageContext in all scopes.
 *
 * @author Dmitri Plotnikov
 * @version $Revision: 1.1 $ $Date: 2001/09/08 21:01:00 $
 */
public class PageContextHandler implements DynamicPropertyHandler {

    public String[] getPropertyNames(Object pageContext){
        ArrayList list = new ArrayList();
        Enumeration e = ((PageContext)pageContext).getAttributeNamesInScope(PageContext.PAGE_SCOPE);
        while (e.hasMoreElements()){
            list.add(e.nextElement());
        }
        e = ((PageContext)pageContext).getAttributeNamesInScope(PageContext.REQUEST_SCOPE);
        while (e.hasMoreElements()){
            list.add(e.nextElement());
        }
        e = ((PageContext)pageContext).getAttributeNamesInScope(PageContext.SESSION_SCOPE);
        while (e.hasMoreElements()){
            list.add(e.nextElement());
        }
        e = ((PageContext)pageContext).getAttributeNamesInScope(PageContext.APPLICATION_SCOPE);
        while (e.hasMoreElements()){
            list.add(e.nextElement());
        }
        return (String[])list.toArray(new String[0]);
    }

    /**
     * Returns <code>pageContext.findAttribute(property)</code>.
     */
    public Object getProperty(Object pageContext, String property){
        return ((PageContext)pageContext).findAttribute(property);
    }

    public void setProperty(Object pageContext, String property, Object value){
        ((PageContext)pageContext).setAttribute(property, value, PageContext.PAGE_SCOPE);
    }
}
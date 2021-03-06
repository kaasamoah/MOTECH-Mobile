/**
 * MOTECH PLATFORM OPENSOURCE LICENSE AGREEMENT
 *
 * Copyright (c) 2010-11 The Trustees of Columbia University in the City of
 * New York and Grameen Foundation USA.  All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * 3. Neither the name of Grameen Foundation USA, Columbia University, or
 * their respective contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY GRAMEEN FOUNDATION USA, COLUMBIA UNIVERSITY
 * AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING,
 * BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL GRAMEEN FOUNDATION
 * USA, COLUMBIA UNIVERSITY OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
 * OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.motechproject.mobile.imp.util;

import java.util.List;
import org.motechproject.mobile.core.manager.CoreManager;
import org.motechproject.mobile.core.model.IncMessageFormParameterStatus;
import org.motechproject.mobile.core.model.IncomingMessageForm;
import org.motechproject.mobile.core.model.IncomingMessageFormParameter;

/**
 * Validates an IncominMessageForm
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 */
public class CompositeRequirementValidator {

    private List<String> fields;
    private int requiredMatches;

    /**
     * Checks a form for the existence of a minimum number of a specified group of fields
     *
     * @param form The form to validate
     * @param coreManager Utility class for creating missing fields
     * @return
     */
    public boolean validate(IncomingMessageForm form, CoreManager coreManager) {
        boolean valid = false;
        int matchCount = 0;
        String fieldName = "";

        for (String field : fields) {
            //Retrieve the name of the first field in the group. 
            //This field will be used to store the error should the validation fail.
            if (fieldName.isEmpty()) {
                fieldName = field;
            }

            if (form.getIncomingMsgFormParameters().containsKey(field.toLowerCase())) {
                matchCount++;
            }
        }

        if (matchCount >= requiredMatches) {
            valid = true;
        } else {
            String error = "At least " + requiredMatches + " of the fields below required:";

            IncomingMessageFormParameter param = coreManager.createIncomingMessageFormParameter();
            param.setName(fieldName.toLowerCase());
            param.setMessageFormParamStatus(IncMessageFormParameterStatus.INVALID);

            for (String field : fields) {
                error += "\n" + field;
            }
            param.setErrCode(1);
            param.setErrText(error);

            form.getIncomingMsgFormParameters().put(fieldName.toLowerCase(), param);
        }
        return valid;
    }

    /**
     * @param fields the fields to set
     */
    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    /**
     * @param requiredMatches the requiredMatches to set
     */
    public void setRequiredMatches(int requiredMatches) {
        this.requiredMatches = requiredMatches;
    }
}

/*
 * Copyright 2011 Global Biodiversity Information Facility (GBIF)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gbif.parser.xml.model;

import java.io.Serializable;

/**
 * This class represents an identifier for a RawOccurrenceRecord. For historical (ie old MySQL
 * schema) reasons the types have numbers. The only really interesting one is type 7: meant as a
 * guid, and usable as the occurrenceId.
 */
public class IdentifierRecord implements Serializable {

  private static final long serialVersionUID = 7608063144083531629L;

  // TODO: change this to int backed enum
  private Integer identifierType;
  private String identifier;

  public static final int OCCURRENCE_ID_TYPE = 7;

  public Integer getIdentifierType() {
    return identifierType;
  }

  public void setIdentifierType(Integer identifierType) {
    this.identifierType = identifierType;
  }

  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }
}
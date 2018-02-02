/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package org.gbif.pipelines.io.avro;  
@SuppressWarnings("all")
/** A container for temporal fields of DarwinCore that have been interpreted. */
@org.apache.avro.specific.AvroGenerated
public class TemporalRecord extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"TemporalRecord\",\"namespace\":\"org.gbif.pipelines.io.avro\",\"doc\":\"A container for temporal fields of DarwinCore that have been interpreted.\",\"fields\":[{\"name\":\"id\",\"type\":\"string\",\"doc\":\"The record id\"},{\"name\":\"year\",\"type\":[\"null\",\"int\"],\"default\":null},{\"name\":\"month\",\"type\":[\"null\",\"int\"],\"default\":null},{\"name\":\"day\",\"type\":[\"null\",\"int\"],\"default\":null},{\"name\":\"startDayOfYear\",\"type\":[\"null\",\"int\"],\"default\":null},{\"name\":\"endDayOfYear\",\"type\":[\"null\",\"int\"],\"default\":null},{\"name\":\"modified\",\"type\":[\"null\",\"long\"],\"default\":null,\"logicalType\":\"timestamp-millis\"},{\"name\":\"dateIdentified\",\"type\":[\"null\",\"long\"],\"default\":null,\"logicalType\":\"timestamp-millis\"},{\"name\":\"eventTime\",\"type\":[\"null\",\"long\"],\"default\":null,\"logicalType\":\"time-millis\"},{\"name\":\"eventDate\",\"type\":[\"null\",\"long\"],\"default\":null,\"logicalType\":\"timestamp-millis\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  /** The record id */
  @Deprecated public java.lang.CharSequence id;
  @Deprecated public java.lang.Integer year;
  @Deprecated public java.lang.Integer month;
  @Deprecated public java.lang.Integer day;
  @Deprecated public java.lang.Integer startDayOfYear;
  @Deprecated public java.lang.Integer endDayOfYear;
  @Deprecated public java.lang.Long modified;
  @Deprecated public java.lang.Long dateIdentified;
  @Deprecated public java.lang.Long eventTime;
  @Deprecated public java.lang.Long eventDate;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>. 
   */
  public TemporalRecord() {}

  /**
   * All-args constructor.
   */
  public TemporalRecord(java.lang.CharSequence id, java.lang.Integer year, java.lang.Integer month, java.lang.Integer day, java.lang.Integer startDayOfYear, java.lang.Integer endDayOfYear, java.lang.Long modified, java.lang.Long dateIdentified, java.lang.Long eventTime, java.lang.Long eventDate) {
    this.id = id;
    this.year = year;
    this.month = month;
    this.day = day;
    this.startDayOfYear = startDayOfYear;
    this.endDayOfYear = endDayOfYear;
    this.modified = modified;
    this.dateIdentified = dateIdentified;
    this.eventTime = eventTime;
    this.eventDate = eventDate;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return id;
    case 1: return year;
    case 2: return month;
    case 3: return day;
    case 4: return startDayOfYear;
    case 5: return endDayOfYear;
    case 6: return modified;
    case 7: return dateIdentified;
    case 8: return eventTime;
    case 9: return eventDate;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: id = (java.lang.CharSequence)value$; break;
    case 1: year = (java.lang.Integer)value$; break;
    case 2: month = (java.lang.Integer)value$; break;
    case 3: day = (java.lang.Integer)value$; break;
    case 4: startDayOfYear = (java.lang.Integer)value$; break;
    case 5: endDayOfYear = (java.lang.Integer)value$; break;
    case 6: modified = (java.lang.Long)value$; break;
    case 7: dateIdentified = (java.lang.Long)value$; break;
    case 8: eventTime = (java.lang.Long)value$; break;
    case 9: eventDate = (java.lang.Long)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'id' field.
   * The record id   */
  public java.lang.CharSequence getId() {
    return id;
  }

  /**
   * Sets the value of the 'id' field.
   * The record id   * @param value the value to set.
   */
  public void setId(java.lang.CharSequence value) {
    this.id = value;
  }

  /**
   * Gets the value of the 'year' field.
   */
  public java.lang.Integer getYear() {
    return year;
  }

  /**
   * Sets the value of the 'year' field.
   * @param value the value to set.
   */
  public void setYear(java.lang.Integer value) {
    this.year = value;
  }

  /**
   * Gets the value of the 'month' field.
   */
  public java.lang.Integer getMonth() {
    return month;
  }

  /**
   * Sets the value of the 'month' field.
   * @param value the value to set.
   */
  public void setMonth(java.lang.Integer value) {
    this.month = value;
  }

  /**
   * Gets the value of the 'day' field.
   */
  public java.lang.Integer getDay() {
    return day;
  }

  /**
   * Sets the value of the 'day' field.
   * @param value the value to set.
   */
  public void setDay(java.lang.Integer value) {
    this.day = value;
  }

  /**
   * Gets the value of the 'startDayOfYear' field.
   */
  public java.lang.Integer getStartDayOfYear() {
    return startDayOfYear;
  }

  /**
   * Sets the value of the 'startDayOfYear' field.
   * @param value the value to set.
   */
  public void setStartDayOfYear(java.lang.Integer value) {
    this.startDayOfYear = value;
  }

  /**
   * Gets the value of the 'endDayOfYear' field.
   */
  public java.lang.Integer getEndDayOfYear() {
    return endDayOfYear;
  }

  /**
   * Sets the value of the 'endDayOfYear' field.
   * @param value the value to set.
   */
  public void setEndDayOfYear(java.lang.Integer value) {
    this.endDayOfYear = value;
  }

  /**
   * Gets the value of the 'modified' field.
   */
  public java.lang.Long getModified() {
    return modified;
  }

  /**
   * Sets the value of the 'modified' field.
   * @param value the value to set.
   */
  public void setModified(java.lang.Long value) {
    this.modified = value;
  }

  /**
   * Gets the value of the 'dateIdentified' field.
   */
  public java.lang.Long getDateIdentified() {
    return dateIdentified;
  }

  /**
   * Sets the value of the 'dateIdentified' field.
   * @param value the value to set.
   */
  public void setDateIdentified(java.lang.Long value) {
    this.dateIdentified = value;
  }

  /**
   * Gets the value of the 'eventTime' field.
   */
  public java.lang.Long getEventTime() {
    return eventTime;
  }

  /**
   * Sets the value of the 'eventTime' field.
   * @param value the value to set.
   */
  public void setEventTime(java.lang.Long value) {
    this.eventTime = value;
  }

  /**
   * Gets the value of the 'eventDate' field.
   */
  public java.lang.Long getEventDate() {
    return eventDate;
  }

  /**
   * Sets the value of the 'eventDate' field.
   * @param value the value to set.
   */
  public void setEventDate(java.lang.Long value) {
    this.eventDate = value;
  }

  /** Creates a new TemporalRecord RecordBuilder */
  public static org.gbif.pipelines.io.avro.TemporalRecord.Builder newBuilder() {
    return new org.gbif.pipelines.io.avro.TemporalRecord.Builder();
  }
  
  /** Creates a new TemporalRecord RecordBuilder by copying an existing Builder */
  public static org.gbif.pipelines.io.avro.TemporalRecord.Builder newBuilder(org.gbif.pipelines.io.avro.TemporalRecord.Builder other) {
    return new org.gbif.pipelines.io.avro.TemporalRecord.Builder(other);
  }
  
  /** Creates a new TemporalRecord RecordBuilder by copying an existing TemporalRecord instance */
  public static org.gbif.pipelines.io.avro.TemporalRecord.Builder newBuilder(org.gbif.pipelines.io.avro.TemporalRecord other) {
    return new org.gbif.pipelines.io.avro.TemporalRecord.Builder(other);
  }
  
  /**
   * RecordBuilder for TemporalRecord instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<TemporalRecord>
    implements org.apache.avro.data.RecordBuilder<TemporalRecord> {

    private java.lang.CharSequence id;
    private java.lang.Integer year;
    private java.lang.Integer month;
    private java.lang.Integer day;
    private java.lang.Integer startDayOfYear;
    private java.lang.Integer endDayOfYear;
    private java.lang.Long modified;
    private java.lang.Long dateIdentified;
    private java.lang.Long eventTime;
    private java.lang.Long eventDate;

    /** Creates a new Builder */
    private Builder() {
      super(org.gbif.pipelines.io.avro.TemporalRecord.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(org.gbif.pipelines.io.avro.TemporalRecord.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.year)) {
        this.year = data().deepCopy(fields()[1].schema(), other.year);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.month)) {
        this.month = data().deepCopy(fields()[2].schema(), other.month);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.day)) {
        this.day = data().deepCopy(fields()[3].schema(), other.day);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.startDayOfYear)) {
        this.startDayOfYear = data().deepCopy(fields()[4].schema(), other.startDayOfYear);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.endDayOfYear)) {
        this.endDayOfYear = data().deepCopy(fields()[5].schema(), other.endDayOfYear);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.modified)) {
        this.modified = data().deepCopy(fields()[6].schema(), other.modified);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.dateIdentified)) {
        this.dateIdentified = data().deepCopy(fields()[7].schema(), other.dateIdentified);
        fieldSetFlags()[7] = true;
      }
      if (isValidValue(fields()[8], other.eventTime)) {
        this.eventTime = data().deepCopy(fields()[8].schema(), other.eventTime);
        fieldSetFlags()[8] = true;
      }
      if (isValidValue(fields()[9], other.eventDate)) {
        this.eventDate = data().deepCopy(fields()[9].schema(), other.eventDate);
        fieldSetFlags()[9] = true;
      }
    }
    
    /** Creates a Builder by copying an existing TemporalRecord instance */
    private Builder(org.gbif.pipelines.io.avro.TemporalRecord other) {
            super(org.gbif.pipelines.io.avro.TemporalRecord.SCHEMA$);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.year)) {
        this.year = data().deepCopy(fields()[1].schema(), other.year);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.month)) {
        this.month = data().deepCopy(fields()[2].schema(), other.month);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.day)) {
        this.day = data().deepCopy(fields()[3].schema(), other.day);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.startDayOfYear)) {
        this.startDayOfYear = data().deepCopy(fields()[4].schema(), other.startDayOfYear);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.endDayOfYear)) {
        this.endDayOfYear = data().deepCopy(fields()[5].schema(), other.endDayOfYear);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.modified)) {
        this.modified = data().deepCopy(fields()[6].schema(), other.modified);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.dateIdentified)) {
        this.dateIdentified = data().deepCopy(fields()[7].schema(), other.dateIdentified);
        fieldSetFlags()[7] = true;
      }
      if (isValidValue(fields()[8], other.eventTime)) {
        this.eventTime = data().deepCopy(fields()[8].schema(), other.eventTime);
        fieldSetFlags()[8] = true;
      }
      if (isValidValue(fields()[9], other.eventDate)) {
        this.eventDate = data().deepCopy(fields()[9].schema(), other.eventDate);
        fieldSetFlags()[9] = true;
      }
    }

    /** Gets the value of the 'id' field */
    public java.lang.CharSequence getId() {
      return id;
    }
    
    /** Sets the value of the 'id' field */
    public org.gbif.pipelines.io.avro.TemporalRecord.Builder setId(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.id = value;
      fieldSetFlags()[0] = true;
      return this; 
    }
    
    /** Checks whether the 'id' field has been set */
    public boolean hasId() {
      return fieldSetFlags()[0];
    }
    
    /** Clears the value of the 'id' field */
    public org.gbif.pipelines.io.avro.TemporalRecord.Builder clearId() {
      id = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /** Gets the value of the 'year' field */
    public java.lang.Integer getYear() {
      return year;
    }
    
    /** Sets the value of the 'year' field */
    public org.gbif.pipelines.io.avro.TemporalRecord.Builder setYear(java.lang.Integer value) {
      validate(fields()[1], value);
      this.year = value;
      fieldSetFlags()[1] = true;
      return this; 
    }
    
    /** Checks whether the 'year' field has been set */
    public boolean hasYear() {
      return fieldSetFlags()[1];
    }
    
    /** Clears the value of the 'year' field */
    public org.gbif.pipelines.io.avro.TemporalRecord.Builder clearYear() {
      year = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /** Gets the value of the 'month' field */
    public java.lang.Integer getMonth() {
      return month;
    }
    
    /** Sets the value of the 'month' field */
    public org.gbif.pipelines.io.avro.TemporalRecord.Builder setMonth(java.lang.Integer value) {
      validate(fields()[2], value);
      this.month = value;
      fieldSetFlags()[2] = true;
      return this; 
    }
    
    /** Checks whether the 'month' field has been set */
    public boolean hasMonth() {
      return fieldSetFlags()[2];
    }
    
    /** Clears the value of the 'month' field */
    public org.gbif.pipelines.io.avro.TemporalRecord.Builder clearMonth() {
      month = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /** Gets the value of the 'day' field */
    public java.lang.Integer getDay() {
      return day;
    }
    
    /** Sets the value of the 'day' field */
    public org.gbif.pipelines.io.avro.TemporalRecord.Builder setDay(java.lang.Integer value) {
      validate(fields()[3], value);
      this.day = value;
      fieldSetFlags()[3] = true;
      return this; 
    }
    
    /** Checks whether the 'day' field has been set */
    public boolean hasDay() {
      return fieldSetFlags()[3];
    }
    
    /** Clears the value of the 'day' field */
    public org.gbif.pipelines.io.avro.TemporalRecord.Builder clearDay() {
      day = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /** Gets the value of the 'startDayOfYear' field */
    public java.lang.Integer getStartDayOfYear() {
      return startDayOfYear;
    }
    
    /** Sets the value of the 'startDayOfYear' field */
    public org.gbif.pipelines.io.avro.TemporalRecord.Builder setStartDayOfYear(java.lang.Integer value) {
      validate(fields()[4], value);
      this.startDayOfYear = value;
      fieldSetFlags()[4] = true;
      return this; 
    }
    
    /** Checks whether the 'startDayOfYear' field has been set */
    public boolean hasStartDayOfYear() {
      return fieldSetFlags()[4];
    }
    
    /** Clears the value of the 'startDayOfYear' field */
    public org.gbif.pipelines.io.avro.TemporalRecord.Builder clearStartDayOfYear() {
      startDayOfYear = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /** Gets the value of the 'endDayOfYear' field */
    public java.lang.Integer getEndDayOfYear() {
      return endDayOfYear;
    }
    
    /** Sets the value of the 'endDayOfYear' field */
    public org.gbif.pipelines.io.avro.TemporalRecord.Builder setEndDayOfYear(java.lang.Integer value) {
      validate(fields()[5], value);
      this.endDayOfYear = value;
      fieldSetFlags()[5] = true;
      return this; 
    }
    
    /** Checks whether the 'endDayOfYear' field has been set */
    public boolean hasEndDayOfYear() {
      return fieldSetFlags()[5];
    }
    
    /** Clears the value of the 'endDayOfYear' field */
    public org.gbif.pipelines.io.avro.TemporalRecord.Builder clearEndDayOfYear() {
      endDayOfYear = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /** Gets the value of the 'modified' field */
    public java.lang.Long getModified() {
      return modified;
    }
    
    /** Sets the value of the 'modified' field */
    public org.gbif.pipelines.io.avro.TemporalRecord.Builder setModified(java.lang.Long value) {
      validate(fields()[6], value);
      this.modified = value;
      fieldSetFlags()[6] = true;
      return this; 
    }
    
    /** Checks whether the 'modified' field has been set */
    public boolean hasModified() {
      return fieldSetFlags()[6];
    }
    
    /** Clears the value of the 'modified' field */
    public org.gbif.pipelines.io.avro.TemporalRecord.Builder clearModified() {
      modified = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    /** Gets the value of the 'dateIdentified' field */
    public java.lang.Long getDateIdentified() {
      return dateIdentified;
    }
    
    /** Sets the value of the 'dateIdentified' field */
    public org.gbif.pipelines.io.avro.TemporalRecord.Builder setDateIdentified(java.lang.Long value) {
      validate(fields()[7], value);
      this.dateIdentified = value;
      fieldSetFlags()[7] = true;
      return this; 
    }
    
    /** Checks whether the 'dateIdentified' field has been set */
    public boolean hasDateIdentified() {
      return fieldSetFlags()[7];
    }
    
    /** Clears the value of the 'dateIdentified' field */
    public org.gbif.pipelines.io.avro.TemporalRecord.Builder clearDateIdentified() {
      dateIdentified = null;
      fieldSetFlags()[7] = false;
      return this;
    }

    /** Gets the value of the 'eventTime' field */
    public java.lang.Long getEventTime() {
      return eventTime;
    }
    
    /** Sets the value of the 'eventTime' field */
    public org.gbif.pipelines.io.avro.TemporalRecord.Builder setEventTime(java.lang.Long value) {
      validate(fields()[8], value);
      this.eventTime = value;
      fieldSetFlags()[8] = true;
      return this; 
    }
    
    /** Checks whether the 'eventTime' field has been set */
    public boolean hasEventTime() {
      return fieldSetFlags()[8];
    }
    
    /** Clears the value of the 'eventTime' field */
    public org.gbif.pipelines.io.avro.TemporalRecord.Builder clearEventTime() {
      eventTime = null;
      fieldSetFlags()[8] = false;
      return this;
    }

    /** Gets the value of the 'eventDate' field */
    public java.lang.Long getEventDate() {
      return eventDate;
    }
    
    /** Sets the value of the 'eventDate' field */
    public org.gbif.pipelines.io.avro.TemporalRecord.Builder setEventDate(java.lang.Long value) {
      validate(fields()[9], value);
      this.eventDate = value;
      fieldSetFlags()[9] = true;
      return this; 
    }
    
    /** Checks whether the 'eventDate' field has been set */
    public boolean hasEventDate() {
      return fieldSetFlags()[9];
    }
    
    /** Clears the value of the 'eventDate' field */
    public org.gbif.pipelines.io.avro.TemporalRecord.Builder clearEventDate() {
      eventDate = null;
      fieldSetFlags()[9] = false;
      return this;
    }

    @Override
    public TemporalRecord build() {
      try {
        TemporalRecord record = new TemporalRecord();
        record.id = fieldSetFlags()[0] ? this.id : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.year = fieldSetFlags()[1] ? this.year : (java.lang.Integer) defaultValue(fields()[1]);
        record.month = fieldSetFlags()[2] ? this.month : (java.lang.Integer) defaultValue(fields()[2]);
        record.day = fieldSetFlags()[3] ? this.day : (java.lang.Integer) defaultValue(fields()[3]);
        record.startDayOfYear = fieldSetFlags()[4] ? this.startDayOfYear : (java.lang.Integer) defaultValue(fields()[4]);
        record.endDayOfYear = fieldSetFlags()[5] ? this.endDayOfYear : (java.lang.Integer) defaultValue(fields()[5]);
        record.modified = fieldSetFlags()[6] ? this.modified : (java.lang.Long) defaultValue(fields()[6]);
        record.dateIdentified = fieldSetFlags()[7] ? this.dateIdentified : (java.lang.Long) defaultValue(fields()[7]);
        record.eventTime = fieldSetFlags()[8] ? this.eventTime : (java.lang.Long) defaultValue(fields()[8]);
        record.eventDate = fieldSetFlags()[9] ? this.eventDate : (java.lang.Long) defaultValue(fields()[9]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}

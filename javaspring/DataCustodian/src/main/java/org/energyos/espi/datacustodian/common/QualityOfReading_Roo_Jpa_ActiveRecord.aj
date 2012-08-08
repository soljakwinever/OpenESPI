/*******************************************************************************
 * Copyright (c) 2011, 2012 EnergyOS.Org
 *
 * Licensed by EnergyOS.Org under one or more contributor license agreements.
 * See the NOTICE file distributed with this work for additional information
 * regarding copyright ownership.  The EnergyOS.org licenses this file
 * to you under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at:
 *  
 *   http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *  
 ******************************************************************************
*/

// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.energyos.espi.datacustodian.common;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.energyos.espi.datacustodian.common.QualityOfReading;
import org.springframework.transaction.annotation.Transactional;

privileged aspect QualityOfReading_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager QualityOfReading.entityManager;
    
    public static final EntityManager QualityOfReading.entityManager() {
        EntityManager em = new QualityOfReading().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long QualityOfReading.countQualityOfReadings() {
        return entityManager().createQuery("SELECT COUNT(o) FROM QualityOfReading o", Long.class).getSingleResult();
    }
    
    public static List<QualityOfReading> QualityOfReading.findAllQualityOfReadings() {
        return entityManager().createQuery("SELECT o FROM QualityOfReading o", QualityOfReading.class).getResultList();
    }
    
    public static QualityOfReading QualityOfReading.findQualityOfReading(Long id) {
        if (id == null) return null;
        return entityManager().find(QualityOfReading.class, id);
    }
    
    public static List<QualityOfReading> QualityOfReading.findQualityOfReadingEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM QualityOfReading o", QualityOfReading.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void QualityOfReading.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void QualityOfReading.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            QualityOfReading attached = QualityOfReading.findQualityOfReading(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void QualityOfReading.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void QualityOfReading.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public QualityOfReading QualityOfReading.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        QualityOfReading merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
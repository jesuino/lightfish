/*
Copyright 2012 Adam Bien, adam-bien.com

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package org.lightfish.business.monitoring.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Adam Bien, blog.adam-bien.com
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@Entity
public class Snapshot {
    
    @Id
    @GeneratedValue
    private long id;
    @Temporal(TemporalType.TIME)
    private Date monitoringTime;
    private long usedHeapSize;
    private int threadCount;
    private int peakThreadCount;
    private int totalErrors;
    private int currentThreadBusy;
    private int committedTX;
    private int rolledBackTX;
    private int queuedConnections;
    private int activeSessions;
    private int expiredSessions;
    private boolean escalated;
    private String escalationReason;
    private String deadlockedThreads;

    @XmlTransient @Transient
    private String escalationChannel;
    
    @OneToMany(cascade= CascadeType.PERSIST)
    private List<ConnectionPool> pools;

    @OneToMany(cascade= CascadeType.PERSIST)
    private List<Application> apps;


    
    public Snapshot() {
        this.monitoringTime = new Date();
        this.pools = new ArrayList<>();
        this.apps = new ArrayList<>();
    }

    public void add(ConnectionPool connectionPool) {
        this.pools.add(connectionPool);
    }

    public void add(Application app) {
        this.apps.add(app);
    }

    public long getId() {
        return id;
    }

    public Date getMonitoringTime() {
        return monitoringTime;
    }

    public long getUsedHeapSize() {
        return usedHeapSize;
    }

    public int getThreadCount() {
        return threadCount;
    }

    public int getPeakThreadCount() {
        return peakThreadCount;
    }

    public int getTotalErrors() {
        return totalErrors;
    }

    public int getCurrentThreadBusy() {
        return currentThreadBusy;
    }

    public int getCommittedTX() {
        return committedTX;
    }

    public int getRolledBackTX() {
        return rolledBackTX;
    }

    public int getQueuedConnections() {
        return queuedConnections;
    }

    public int getActiveSessions() {
        return activeSessions;
    }

    public int getExpiredSessions() {
        return expiredSessions;
    }

    public String getDeadlockedThreads() {
        return deadlockedThreads;
    }
    
    

    public List<ConnectionPool> getPools() {
        return pools;
    }

    public List<Application> getApps() {
        return apps;
    }

    public String getEscalationChannel() {
        return escalationChannel;
    }

    public void setEscalationChannel(String escalationChannel) {
        this.escalationChannel = escalationChannel;
    }

    public boolean isEscalated() {
        return escalated;
    }

    public void setEscalated(boolean escalated) {
        this.escalated = escalated;
    }

    public String getEscalationReason() {
        return escalationReason;
    }

    public void setEscalationReason(String escalationReason) {
        this.escalationReason = escalationReason;
    }

    public static class Builder{
        private Snapshot snapshot;

        public Builder() {
            this.snapshot = new Snapshot();
        }
        
        public Builder usedHeapSize(long usedHeapSize){
            snapshot.usedHeapSize = usedHeapSize;
            return this;
        }
        
        public Builder expiredSessions(int expiredSessions){
            snapshot.expiredSessions = expiredSessions;
            return this;
        }

        public Builder threadCount(int threadCount){
            snapshot.threadCount = threadCount;            
            return this;
        }

        public Builder peakThreadCount(int peakThreadCount){
            snapshot.peakThreadCount = peakThreadCount;
            return this;
        }

        public Builder totalErrors(int totalErrors){
            snapshot.totalErrors = totalErrors;
            return this;
        }
        public Builder currentThreadBusy(int currentThreadBusy){
            snapshot.currentThreadBusy = currentThreadBusy;
            return this;

        }
        public Builder committedTX(int committedTX){
            snapshot.committedTX = committedTX;
            return this;
        }
        public Builder rolledBackTX(int rolledBackTX){
            snapshot.rolledBackTX = rolledBackTX;
            return this;
        }
        public Builder queuedConnections(int queuedConnections){
            snapshot.queuedConnections = queuedConnections;
            return this;
        }
        public Builder activeSessions(int activeSessions){
            snapshot.activeSessions = activeSessions;
            return this;
        }
    
        public Builder deadlockedThreads(String deadlockedThreads){
            snapshot.deadlockedThreads = deadlockedThreads;
            return this;
        }

        
        public Snapshot build(){
            return this.snapshot;
        }
    
    }

    @Override
    public String toString() {
        return "Snapshot{" +
                "id=" + id +
                ", monitoringTime=" + monitoringTime +
                ", usedHeapSize=" + usedHeapSize +
                ", threadCount=" + threadCount +
                ", peakThreadCount=" + peakThreadCount +
                ", totalErrors=" + totalErrors +
                ", currentThreadBusy=" + currentThreadBusy +
                ", committedTX=" + committedTX +
                ", rolledBackTX=" + rolledBackTX +
                ", queuedConnections=" + queuedConnections +
                ", activeSessions=" + activeSessions +
                ", expiredSessions=" + expiredSessions +
                ", pools=" + pools +
                '}';
    }
}

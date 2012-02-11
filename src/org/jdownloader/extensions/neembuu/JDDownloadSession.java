/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jdownloader.extensions.neembuu;

import java.util.concurrent.atomic.AtomicReference;
import jd.http.URLConnectionAdapter;
import jd.plugins.DownloadLink;
import jd.plugins.PluginForHost;
import jd.plugins.download.DownloadInterface;

/**
 *
 * @author Shashank Tulsyan
 */
public final class JDDownloadSession {
    private final DownloadLink downloadLink;
    private final DownloadInterface di;
    private final PluginForHost plugin;
    private final URLConnectionAdapter connection;
    
    private final AtomicReference<WatchAsYouDownloadSession> watchAsYouDownloadSessionRef 
            = new AtomicReference<WatchAsYouDownloadSession>(null);
    
    public JDDownloadSession(DownloadLink downloadLink, DownloadInterface di, PluginForHost plugin, URLConnectionAdapter connection) {
        this.downloadLink = downloadLink;
        this.di = di;
        this.plugin = plugin;
        this.connection = connection;
    }

    public final URLConnectionAdapter getURLConnectionAdapter() {
        return connection;
    }

    public final DownloadInterface getDownloadInterface() {
        return di;
    }

    public final DownloadLink getDownloadLink() {
        return downloadLink;
    }

    public final PluginForHost getPluginForHost() {
        return plugin;
    }
    
    void setWatchAsYouDownloadSession(WatchAsYouDownloadSession wayds){
        if(!watchAsYouDownloadSessionRef.compareAndSet(null, wayds)){
            throw new IllegalStateException("Already initialized");
        }
    }

    public final WatchAsYouDownloadSession getWatchAsYouDownloadSession() {
        if(watchAsYouDownloadSessionRef.get()==null)
            throw new IllegalArgumentException("Not initialized");
        return watchAsYouDownloadSessionRef.get();
    }

    @Override
    public String toString() {
        return downloadLink.toString();
    }
}

package com.league2.app.Module;

import android.app.Application;

import java.util.Arrays;
import java.util.List;

import dagger.ObjectGraph;

public class DaggerApplication extends Application{
    private ObjectGraph graph;

    @Override
    public void onCreate() {
        super.onCreate();
        graph = ObjectGraph.create(getModules().toArray());
    }

    protected List<LeagueModule> getModules() {
        return Arrays.asList(
                new LeagueModule(this)
        );
    }
    public void inject(Object object) {
        graph.inject(object);
    }
}

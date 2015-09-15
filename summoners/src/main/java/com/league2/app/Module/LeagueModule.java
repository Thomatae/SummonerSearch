package com.league2.app.Module;

import android.content.Context;
import android.view.LayoutInflater;
import com.league2.app.fragment.GeneralInfoFragment;
import com.league2.app.fragment.Homepage;
import com.league2.app.fragment.RecentGamesFragment;
import com.league2.app.Service.LeagueApi;

import org.codehaus.jackson.map.ObjectMapper;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;

@Module(injects = {Homepage.class,
                   GeneralInfoFragment.class,
                   RecentGamesFragment.class},
        library = true, complete = false)
public class LeagueModule {
    private final DaggerApplication application;
    private final ObjectMapper mapper = new ObjectMapper();
    private static String LEAGUE_API = "https://na.api.pvp.net/api/lol/na";

    public LeagueModule(DaggerApplication application) {
        this.application = application;
    }

    //TODO create another RestAdapter, one for static data
    //TODo will need another StaticApi
    //TODO probably need to make a custom class

    @Provides @Singleton
    RestAdapter provideRestAdapter() {
        return new RestAdapter.Builder().setEndpoint(LEAGUE_API).setConverter(new JacksonConverter(mapper)).build();
    }

    @Provides
    @Singleton @ForApplication
    Context provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    LayoutInflater provideLayoutInflator() {
        return (LayoutInflater) application.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Provides
    @Singleton
    public LeagueApi provideLeagueApi(RestAdapter restAdapter) {
        return restAdapter.create(LeagueApi.class);
    }
}

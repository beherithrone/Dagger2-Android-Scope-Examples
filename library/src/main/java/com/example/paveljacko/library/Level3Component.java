package com.example.paveljacko.library;

import dagger.Component;

/**
 * Created by paveljacko on 26/09/15.
 */
@Level3Scope
@Component(dependencies = {Level2Component.class}, modules = {Level3Module.class})
public interface Level3Component {

    //void inject(Level3Fragment level3Fragment);

    Level3Class level3Class();
}

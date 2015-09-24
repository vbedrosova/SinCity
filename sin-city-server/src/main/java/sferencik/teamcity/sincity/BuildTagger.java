package sferencik.teamcity.sincity;

import jetbrains.buildServer.log.Loggers;
import jetbrains.buildServer.serverSide.SRunningBuild;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BuildTagger {
    private final SRunningBuild build;
    private final Map<String, String> sinCityParameters;

    public BuildTagger(SRunningBuild build, Map<String, String> sinCityParameters) {
        this.build = build;
        this.sinCityParameters = sinCityParameters;
    }

    /**
     * Tag the finishing build (if requested in the config) as either
     * 1) triggered by SinCity
     * 2) not triggered by SinCity
     */
    void tagBuild() {
        // tag the finished build
        SettingNames settingNames = new SettingNames();
        String tagParameterName = build.getParametersProvider().get(new ParameterNames().getSincityRangeTopBuildId()) == null
                ? settingNames.getTagNameForBuildsNotTriggeredBySinCity()
                : settingNames.getTagNameForBuildsTriggeredBySinCity();
        final String tagName = sinCityParameters.get(tagParameterName);

        if (tagName == null || tagName.isEmpty())
            return;

        Loggers.SERVER.debug("[SinCity] tagging build with '" + tagName + "'");
        final List<String> resultingTags = new ArrayList<String>(build.getTags());
        resultingTags.add(tagName);
        build.setTags(resultingTags);
    }

}
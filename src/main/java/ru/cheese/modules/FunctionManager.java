package ru.cheese.modules;

import ru.cheese.modules.combat.*;
import ru.cheese.modules.misc.*;
import ru.cheese.modules.misc.ClientSounds;
import ru.cheese.modules.misc.NoCommands;
import ru.cheese.modules.misc.Optimizer;
import ru.cheese.modules.movement.*;
import ru.cheese.modules.player.*;
import ru.cheese.modules.render.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class FunctionManager {

    public static final List<Function> functions = new CopyOnWriteArrayList<>();
    public final ClickGUI clickGUI;
    public final Optimizer optimizer;
    public final ClientSounds clientSounds;
    public final NoFriendDamage noFriendDamage;
    public final NoCommands noCommands;
    public final SwingAnimations swingAnimations;
    public final ViewModel viewModel;
    public final HUD hud;
    public final NoRender noRender;
    public final NameProtect nameProtect;
    public final NoInteract noInteract;
    public final ItemScroller itemScroller;
    public final LittleSnickers littleSnickers;
    public final UnHook unHook;
    public final FullBright fullBright;
    public final ItemPhysic itemPhysic;
    public final ExtraTab extraTab;
    public final FreeLook freeLook;
    public final Arrows arrows;
    public final CustomCoolDown customCoolDown;
    public final NameTags nameTags;
    public final AspectRatio aspectRatio;
    public final World customWorld;
    public final IRC irc;
    public final AutoSprint autoSprint;
    public final CrossHair crossHair;
    public final BlockHighLight blockHighLight;
    //public final KTLeave ktLeave;
    //public final MaceExploit maceExploit;
    public FunctionManager() {
        functions.addAll(Arrays.asList(
                //Combat
                noFriendDamage = new NoFriendDamage(),
                //Misc
                unHook = new UnHook(),
                optimizer = new Optimizer(),
                clientSounds = new ClientSounds(),
                new DeathCoords(),
                new ServerRPSpoff(),
                new FTHelper(),
                new HWHelper(),
                new RWHelper(),
                new DiscordRCP(),
                irc = new IRC(),
                nameProtect = new NameProtect(),
                noCommands = new NoCommands(),
                //Movement
                autoSprint = new AutoSprint(),
                freeLook = new FreeLook(),
                //Player
                new AutoLeave(),
                itemScroller = new ItemScroller(),
                new AutoRespawn(),
                customCoolDown = new CustomCoolDown(),
                new MiddleClickFriend(),
                noInteract = new NoInteract(),
      //          maceExploit = new MaceExploit(),
           //     new GodMode(),
                //Render
                clickGUI = new ClickGUI(),
                hud = new HUD(),
                swingAnimations = new SwingAnimations(),
                viewModel = new ViewModel(),
                aspectRatio = new AspectRatio(),
                crossHair = new CrossHair(),
                fullBright = new FullBright(),
           //     new ShulkerPreview(),
                customWorld = new World(),
                noRender = new NoRender(),
                itemPhysic = new ItemPhysic(),
                extraTab = new ExtraTab(),
                arrows = new Arrows(),
                nameTags = new NameTags(),
                new Prediction(),
                blockHighLight = new BlockHighLight(),
                new AutoAccept(),
                new JumpCircles(),
                new Breadcrumbs(),
                new Trails(),
                new Particles(),
           //     ktLeave = new KTLeave(),
                littleSnickers = new LittleSnickers()
        ));
    }



    public List<Function> getFunctions() {
        return functions;
    }

    public List<Function> getFunctions(Type category) {
        List<Function> functions = new ArrayList<>();
        for (Function function : getFunctions()) {
            if (function.getCategory() == category) {
                functions.add(function);
            }

        }
        return functions;
    }

    public static Function get(String name) {
        for (Function function : functions) {
            if (function != null && function.name.equalsIgnoreCase(name)) {
                return function;
            }
        }
        return null;
    }
}

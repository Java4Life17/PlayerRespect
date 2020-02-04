# HybridMobs

[![N|Solid](<https://img.icons8.com/nolan/64/discord-logo.png>)](https://discord.gg/CGB2pmD)

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

This plugin was made only for HybridServer, If you want to share it to other servers it is up to you. You're now the owner of this plugin.

In this information page you will see

  - How tu use the plugin
  - Important things to keep in mind
  - Examples

# Adding a custom drop!

- To add a custom drop, first you have to go into your '**Configuration.yml**'
- After this, you will visit this website where you can find all the mob types you can add to this file below

[![N|Solid](<https://img.icons8.com/ios-filled/50/000000/share-2.png>)](https://github.com/proxytimeout/PlayerRespect/blob/master/Mobs)

- After you hace choosen your have decided a mob type, you will go to '**Configuration.yml**' and under '**Mobs**' you will type in a custom name of your mob. Inside you will need 3 feilds which are Type, DropItem, Amount, Chance, and Animation. Next to **Type:**, you will place the name of your mobs.
- Next to **DropItem**, you will place the item that will be dropped. You can find a list from the link below.

[![N|Solid](<https://img.icons8.com/ios-filled/50/000000/share-2.png>)](https://github.com/proxytimeout/PlayerRespect/blob/master/Items)
- After you set up the drop item type, you will need to put how many of that item you want the mob to drop. The amount has no limit. It is from 0 to infinity.
- After you setup the amount, now you need to specify the chance of that item being dropped. It starts from 0 to 100. 0 being a low probability and 100 being all the time.
- After this, you can set an animation to your custom drop. This are the animations you can set for it ("*#Animations [EXPLOSION, SONG, PINATA, ZEUS]
#Descriptions:
#EXPLOSION: Will display an explosion animation where the item was dropped.
#SONG: Will play a nice little end song with a fast beat.
#PINATA: Will act as a pinata explosing and spreading candy everywhere
#ZEUS: Ligthning sound, raid horn sound, and a nice bottle particle!*")
- At the end, you should have something like this ->
**MyCustomDrop:**
    **Type: VILLAGER**
   **DropItem: WHEAT**
   **Amount: 5**
   **Chance: 100**
   **Animation: PINATA**

- After this, you are all done. Now you just have to reload the plugin with the command **/hm reload**. For this command, if you are not op, you will need the permission ***HybridMobs.Reload***.

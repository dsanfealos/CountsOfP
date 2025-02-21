<body>
        <h1 style="text-decoration: underline;">Documentation</h1>
        <h2>API</h2>
        <p>URL = countsofp.com</p>
        <h2 style="text-decoration: underline;">Overview</h2>
        <p>This is an API that anyone can use to calculate the resources and ergo needed to upgrade main character's level, weapons, mechanical arm and P-Organ. It also has a special mode to build up your character, and it will show the resulting stats, weight and cost.</p>
        <h2 style="text-decoration: underline;">Levels</h2>
        <p>In order to understand weapon and arm levels, the API will follow the next rules:</p><br>
        <p>Arm in game: -, I, II, III</p>
        <p>Arm in API: 0, 1, 2, 3</p><br>            
        <p>Weapons in game: 0, +1, +2, +3, +4, +5, +6, +7, +8, +9, +10</p>
        <p>Weapons in API: 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11</p>
        <h2 style="text-decoration: underline;">Endpoints</h2>
        <ul>
            <li>Player</li>
          <span style="color: white; background-color: orange; border-radius: 5px; padding 5px;">POST</span>
            <pre>-Simulate stats
                <span style="color: white; background-color: orange; border-radius: 5px; padding 5px;">POST</span>: /player/simulate
                Body: JSON Example
                    <span class="json">{
                            "initial":{
                            "level":10,
                            "vitality":12,
                            "vigor":15,
                            "capacity":15,
                            "motivity":15,
                            "technique":15,
                            "advance":10
                        },
                        "final":{
                            "level":38,
                            "vitality":15,
                            "vigor":20,
                            "capacity":20,
                            "motivity":20,
                            "technique":20,
                            "advance":15
                        }
                    }</span>
             It gives a response with the result stats after increaseing the attributes.</pre>
             <pre>-Amulet list
                <span class="get">GET</span>: /player/amulet
             It gives a response with a list of all the amulets.</pre>
             <pre>-Amulet
                <span class="get">GET</span>: /player/amulet/{amuletId}
             It gives a response with the data of the amulet with id = amuletId</pre>
             <li>Weapon</li>
             <pre>-Special Weapon
                <span class="get">GET</span>: /weapon/S/{weaponId}
             It gives a response with the data of the special weapon with id = weaponId</pre>
             <pre>-Special Weapon List (with all levels)
                <span class="get">GET</span>: /weapon/S/all
             It gives a response with the list of all the special weapons at all levels (1-6)</pre>
             <pre>-Special Weapon List (only level 1)
                <span class="get">GET</span>: /weapon/S
             It gives a response with the list of all the special weapons at level 1</pre>
             <pre>-Special Weapon Search
                <span class="get">GET</span>: /weapon/S/search?{keyword}
             It gives a response with the list of all special weapons that have a name similar 
             to the keyword.</pre>
             <pre>-Upgrade Special Weapon
                <span class="post">POST</span>: /weapon/S/upgrade
                Body: JSON Example
                    <span class="json">{
                        "weaponName":"Holy sword of the ark",
                        "currentLevel":2,
                        "finalLevel":5
                    }</span>
             It gives a response with the result stats and cost of the weapon upgrade from level 
             A to level B. It includes modified handle, that can have values [null, "motivity", 
             "technique", "advance"].</pre>
             <pre>-Blade
                <span class="get">GET</span>: /weapon/blade/{bladeId}
             It gives a response with the data of the blade with id = bladeId</pre>
             <pre>-Blade List (with all levels)
                <span class="get">GET</span>: /weapon/blade/all
             It gives a response with the list of all the blades at all levels (1-11)</pre>
             <pre>-Blade List (only level 1)
                <span class="get">GET</span>: /weapon/blade
             It gives a response with the list of all the blades at level 1</pre>
             <pre>-Blade Search
                <span class="get">GET</span>: /weapon/blade/search?{keyword}
             It gives a response with the list of all blades that have a name similar 
             to the keyword.</pre>
             <pre>-Handle
                <span class="get">GET</span>: /weapon/handle/{handleId}
             It gives a response with the data of the handle with id = handleId</pre>
             <pre>-Handle List
                <span class="get">GET</span>: /weapon/handle
             It gives a response with the list of all the handles</pre>
             <pre>-Handle Search
                <span class="get">GET</span>: /weapon/handle/search?{keyword}
             It gives a response with the list of all handles that have a name similar 
             to the keyword.</pre>
             <pre>-Upgrade Normal Weapon
                <span class="post">POST</span>: /weapon/N/upgrade
                Body: JSON Example
                    <span class="json">{
                        "bladeName":"Bramble curved sword blade",
                        "currentLevel":3,
                        "finalLevel":11,
                        "handleId":3
                    }</span>
             It gives a response with the result stats and cost of the weapon upgrade from level 
             A to level B. It includes modified handle, that can have values [null, "motivity", 
             "technique", "advance"].</pre>
             <li>Full Build</li>
             <pre>-Upgrade arm
                <span class="post">POST</span>: /build/arm
                Body: JSON Example
                    <span class="json">{
                        "initialLevel":1,
                        "finalLevel":3
                    }</span>
             It gives a response with the cost of materials of upgrading the arm from level A to level B.</pre>
             <pre>-Upgrade P Organ
                <span class="post">POST</span>: /build/p_organ
                Body: JSON Example
                    <span class="json">{
                        "1":4,
                        "2":0,
                        "3":2,
                        "4":1
                    }</span>
             It gives a response with the Quartz cost of upgrading X nodes in each P Organ level.
             <span class="warning">WARNING:</span> 
              * Each level past level 1 needs 2 previous nodes upgraded to unlock. It is needed 2 nodes
              to unlock level 2, 4 nodes to unlock level 3, 6 nodes to unlock level 4, etc.</pre>
              <pre>-Armor
                <span class="get">GET</span>: /build/armor/{armorId}
             It gives a response with the data of the armor with id = armorId.</pre>
             <pre>-Armor List
                <span class="get">GET</span>: /build/armor
             It gives a response with a list of all armors.</pre>
             <pre>-Build
                <span class="post">POST</span>: /build/build
                Body: JSON Example
                    <span class="json">{
                        "initialAttributesBody":{
                            "level":10,
                            "vitality":12,
                            "vigor":15,
                            "capacity":15,
                            "motivity":15,
                            "technique":15,
                            "advance":10
                        },
                        "finalAttributesBody":{
                            "level":38,
                            "vitality":15,
                            "vigor":20,
                            "capacity":20,
                            "motivity":20,
                            "technique":20,
                            "advance":15,
                            "amuletIds":[
                                2,
                                4,
                                15,
                                21,
                                24
                            ]
                        },
                        "isWeaponS": true,
                        "statsWeaponNBody": null,
                        "statsWeaponSBody":{
                            "weaponName":"Holy sword of the ark",
                            "currentLevel":2,
                            "finalLevel":5,
                            "modifier":"motivity"
                        },
                        "armorPiecesIds":[
                            6,
                            34,
                            64,
                            95
                        ] 
                    }</span>
             It gives a response with the result stats of the main character and his weapon, including current 
             weight, and the resources needed (ergo and materials) to upgrade main character from level A to B 
             and weapon from level C to D. It also can add armor pieces and amulets. It includes modified handle
             , that can have values [null, "motivity", "technique", "advance"].
             <span class="warning">WARNING:</span> 
             * Armor pieces must be diferent type (there are 4 types/slots of defensive pieces in the 
             game), and can not be more than 4 pieces.
             * Amulets can not be more than 5 at the same time.</pre>
        </ul>        
    </body>

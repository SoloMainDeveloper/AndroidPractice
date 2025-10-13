package com.example.solomeinandroid.player

import com.example.solomeinandroid.player.presentation.model.PlayerModel
import com.example.solomeinandroid.player.presentation.model.Team

fun getMockPlayers() : List<PlayerModel> {
    return listOf(
        PlayerModel(
            name = "Ethan Cole Arnold",
            nationality = "USA",
            age = 25,
            nick = "Ethan",
            team = Team(
                "NRG",
                "https://liquipedia.net/commons/images/thumb/1/16/NRG_2024_allmode.png/100px-NRG_2024_allmode.png"
            ),
            totalWinningsDollars = 516224,
            imageUrl = "https://liquipedia.net/commons/images/thumb/1/1b/NRG_Ethan_at_Valorant_Champions_2025_Grand_Finals.jpg/" +
                    "600px-NRG_Ethan_at_Valorant_Champions_2025_Grand_Finals.jpg",
            info = "Ethan \"Ethan\" Arnold (born March 2, 2000) is an American player who is currently playing" +
                    " for NRG. He is a former Counter-Strike: Global Offensive player known for his time in NRG Esports and Evil Geniuses."
        ),
        PlayerModel(
            name = "Jake Howlett",
            nationality = "UK",
            age = 30,
            nick = "Boaster",
            team = Team(
                "Fnatic",
                "https://liquipedia.net/commons/images/thumb/f/f9/Fnatic_2020_allmode.png/77px-Fnatic_2020_allmode.png"
            ),
            totalWinningsDollars = 421988,
            imageUrl = "https://liquipedia.net/commons/images/thumb/2/2f/" +
                    "FNC_Boaster_at_VCT_EMEA_Kickoff_2025.jpg/600px-FNC_Boaster_at_VCT_EMEA_Kickoff_2025.jpg",
            info = "Jake \"Boaster\" Howlett (born May 25, 1995) is a British player who is currently playing as" +
                    " an In-game leader for Fnatic. He is a former Counter-Strike: Global Offensive player who played" +
                    " for various different British rosters. He was also a Vlogger/Substitute Midlaner for Excel Esports in League of Legends"
        ),
        PlayerModel(
            name = "Jason Susanto",
            nationality = "Indonesia",
            age = 21,
            nick = "f0rsaken",
            team = Team(
                "Paper Rex",
                "https://liquipedia.net/commons/images/thumb/7/7b/Paper_Rex_darkmode.png/59px-Paper_Rex_darkmode.png"
            ),
            totalWinningsDollars = 368898,
            imageUrl = "https://liquipedia.net/commons/images/thumb/9/97/PRX_f0rsaken_at_Valorant_Champions_2025_-_Day_5.jpg/" +
                    "600px-PRX_f0rsaken_at_Valorant_Champions_2025_-_Day_5.jpg",
            info = "Jason \"f0rsakeN\" Susanto (born March 25, 2004) is an Indonesian player who is currently " +
                    "playing as an In-game leader for Paper Rex. He is a former Counter-Strike: G" +
                    "lobal Offensive player. He is Kevin \"xccurate\" Susanto's younger brother."
        ),
        PlayerModel(
            name = "Аяз Ахметшин",
            nick = "nAts",
            nationality = "Russia",
            age = 23,
            team = Team(
                "Team Liquid",
                "https://liquipedia.net/commons/images/thumb/5/59/Team_Liquid_2024_darkmode.png/44px-Team_Liquid_2024_darkmode.png"
            ),
            totalWinningsDollars = 177718,
            imageUrl = "https://liquipedia.net/commons/images/thumb/0/0e/TL_nAts_at_VCT_EMEA_2025_Stage_2.jpg/" +
                    "600px-TL_nAts_at_VCT_EMEA_2025_Stage_2.jpg",
            info = "Ayaz \"nAts\" Akhmetshin (born July 1, 2002) is a Russian player who is currently playing" +
                    " as an In-game leader for Team Liquid. He is a former Counter-Strike: Global Offensive player."
        ),
        PlayerModel(
            name = "Kajetan Haremski",
            nationality = "Poland",
            age = 21,
            nick = "Kaajak",
            team = Team(
                "Fnatic",
                "https://liquipedia.net/commons/images/thumb/1/16/NRG_2024_allmode.png/100px-NRG_2024_allmode.png"
            ),
            totalWinningsDollars = 516224,
            imageUrl = "https://liquipedia.net/commons/images/thumb/1/1d/FNC_kaajak_at_Valorant_Champions_2025_against_RRQ_-_Day_3.jpg/600px-FNC_kaajak_at_Valorant_Champions_2025_against_RRQ_-_Day_3.jpg",
            info = "Kajetan \"kaajak\" Haremski (born August 8, 2004) is a Polish player who is currently playing for Fnatic."
        ),
        PlayerModel(
            name = "Илья Петров",
            nationality = "Russia",
            age = 23,
            nick = "something",
            team = Team(
                "Paper Rex",
                "https://liquipedia.net/commons/images/thumb/7/7b/Paper_Rex_darkmode.png/59px-Paper_Rex_darkmode.png"
            ),
            totalWinningsDollars = 421988,
            imageUrl = "https://liquipedia.net/commons/images/thumb/7/74/PRX_something_at_Valorant_Champions_Playoffs_-_Day_4.jpg/600px-PRX_something_at_Valorant_Champions_Playoffs_-_Day_4.jpg",
            info = "something joined VALORANT in 2020 where he played for multiple teams at smaller online tournaments before joining Insomnia in 2021 and partipated in Qualifiers for the VCT 2021: Japan Stage 3. He later then continued to compete in Japan Challengers until March 2023 where he joined Paper Rex ahead of the VCT 2023: Pacific League where they won that same year and went on to come 2nd at Champions 2023."
        ),
        PlayerModel(
            name = "Austin Roberts",
            nationality = "USA",
            age = 27,
            nick = "crashies",
            team = Team(
                "Fnatic",
                "https://liquipedia.net/commons/images/thumb/1/16/NRG_2024_allmode.png/100px-NRG_2024_allmode.png"
            ),
            totalWinningsDollars = 421988,
            imageUrl = "https://liquipedia.net/commons/images/thumb/7/74/PRX_something_at_Valorant_Champions_Playoffs_-_Day_4.jpg/600px-PRX_something_at_Valorant_Champions_Playoffs_-_Day_4.jpg",
            info = "something joined VALORANT in 2020 where he played for multiple teams at smaller online tournaments before joining Insomnia in 2021 and partipated in Qualifiers for the VCT 2021: Japan Stage 3. He later then continued to compete in Japan Challengers until March 2023 where he joined Paper Rex ahead of the VCT 2023: Pacific League where they won that same year and went on to come 2nd at Champions 2023."
        ),
        PlayerModel(
            name = "Jordan Montemurro",
            nationality = "USA",
            age = 27,
            nick = "Zellsis",
            team = Team(
                "Sentinels",
                "https://liquipedia.net/commons/images/thumb/1/13/Sentinels_2020_allmode.png/50px-Sentinels_2020_allmode.png"
            ),
            totalWinningsDollars = 421988,
            imageUrl = "https://liquipedia.net/commons/images/thumb/c/c1/Sentinels_Zellsis_at_Masters_Madrid.jpg/600px-Sentinels_Zellsis_at_Masters_Madrid.jpg",
            info = "something joined VALORANT in 2020 where he played for multiple teams at smaller online tournaments before joining Insomnia in 2021 and partipated in Qualifiers for the VCT 2021: Japan Stage 3. He later then continued to compete in Japan Challengers until March 2023 where he joined Paper Rex ahead of the VCT 2023: Pacific League where they won that same year and went on to come 2nd at Champions 2023."
        ),
        PlayerModel(
            name = "Benjy David Fish",
            nationality = "UK",
            age = 21,
            nick = "benjyfishy",
            team = Team(
                "Team Heretics",
                "https://liquipedia.net/commons/images/thumb/a/a4/Team_Heretics_2022_allmode.png/40px-Team_Heretics_2022_allmode.png"
            ),
            totalWinningsDollars = 421988,
            imageUrl = "https://liquipedia.net/commons/images/thumb/0/08/Heretics_benjyfishy_at_Valorant_Champions_2025_Playoffs.jpg/600px-Heretics_benjyfishy_at_Valorant_Champions_2025_Playoffs.jpg",
            info = "something joined VALORANT in 2020 where he played for multiple teams at smaller online tournaments before joining Insomnia in 2021 and partipated in Qualifiers for the VCT 2021: Japan Stage 3. He later then continued to compete in Japan Challengers until March 2023 where he joined Paper Rex ahead of the VCT 2023: Pacific League where they won that same year and went on to come 2nd at Champions 2023."
        )
    )
}
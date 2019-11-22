USE flightpub_group1;

ALTER TABLE users MODIFY `password` VARCHAR(64);

INSERT INTO `users`
            (`username`, 
             `password`, 
             `firstname`,
             `middlename`, 
             `lastname`, 
             `dob`, 
             `email`, 
             `company`, 
             `address`, 
             `phonenumber`)
VALUES      ("timon", 
             "42b81b695c9063452295da5fd53833fbdf7f0801b0a8e9b7dacbfffa86b436d9",/*"7440",*/
             "Timon",
             "Tamekah", 
             "Miranda", 
             "2018-08-18 10:01:36", 
             "donec.porttitor@feugiat.edu", 
             "sed nunc institute", 
             "ap #552-3301 arcu. st.", 
             "(09) 6733 0951"), 
            ("jacob", 
             "7cd788de2f5fa0134bd705bf3fcb45ea9ff54c5f0e804526c128ed32ec37917d",/*"4050",*/
             "Jimon",
             "Kermit", 
             "Branch", 
             "2018-09-26 14:28:34", 
             "mauris.magna.duis@mauris.com", 
             "sed turpis llp", 
             "ap #441-9598 dictum. av.", 
             "(04) 6727 8699"), 
            ("arden", 
             "298647699bcb31c22e6d6486891b637b28f1c3cf4d0cf710ce5ce775f9a1e4d3",/*"1443",*/
             "Arden",
             "Erin", 
             "Duffy", 
             "2019-05-24 18:25:20", 
             "vel@elitetiamlaoreet.net", 
             "ac eleifend corporation", 
             "8035 et, st.", 
             "(07) 7896 4069"), 
            ("sebastian", 
             "6e80132e298a210fe4cc677b43cbc9c0c09a3b8b235c7663a1237d7e61fc53b4",/*"6428",*/
             "Sebastian",
             "Amir", 
             "Delacruz", 
             "2019-01-08 15:42:58", 
             "arcu.vestibulum@seddiam.ca", 
             "maecenas iaculis inc.", 
             "ap #158-5287 curabitur av.", 
             "(08) 2789 1124"), 
            ("colorado", 
             "ae08ef776d536d49e5fea11b34a4f2cf981195fee6ec8cfb058ae2686a9d0166",/*"8063",*/
             "Colorado",
             "Karen", 
             "Finch", 
             "2018-05-02 01:47:49", 
             "vitae.odio.sagittis@eu.ca", 
             "aliquam inc.", 
             "620-5327 per street", 
             "(08) 2088 0831"), 
            ("paki", 
             "3bc098c10285bf1e6e0c956a60dfe2ad4fe360aaa50e2f2d99584e27ec1b12b7",/*"4250",*/
             "Paki",
             "Uriel", 
             "Barnett", 
             "2017-10-11 23:10:47", 
             "lorem@aodiosemper.ca", 
             "ligula aliquam limited", 
             "p.o. box 852, 911 enim avenue", 
             "(08) 3448 6994"), 
            ("linus", 
             "fbdf2bdc4b2a45f3508c8ced68098f58375edbf2fe81ec8fe4b113185670939a",/*"8881",*/
             "Linus",
             "Sophia", 
             "Crawford", 
             "2018-03-17 12:09:34", 
             "dui.lectus.rutrum@commodo.com", 
             "eu tellus eu llc", 
             "310-389 nunc avenue", 
             "(09) 5562 0693"), 
            ("erich", 
             "aff4b5855447e95d8af83a24048c3f3e2320be9f5dde915ba81015e18802e0b2",/*"3285",*/
             "Erich",
             "Joseph", 
             "Bernard", 
             "2018-07-16 12:53:20", 
             "nulla.interdum@tellusnon.co.uk", 
             "suspendisse dui foundation", 
             "ap #127-9498 convallis rd.", 
             "(08) 6216 4492"), 
            ("leonard", 
             "6e237b1f8dc0da15957d168af0b2f58ce8dbc421671f0b38d1f39cc6026af032",/*"7013",*/
             "Leonard",
             "Iona", 
             "Small", 
             "2018-05-27 06:13:31", 
             "fringilla.mi@enim.org", 
             "euismod corporation", 
             "p.o. box 684, 6930 at, road", 
             "(07) 4725 5980"), 
            ("zephania", 
             "b37cdc759f678ab50c4190486dab992f02e2bd0e6a7d18ef36fab68b49712c7a",/*"9892",*/
             "Zephania",
             "Fallon", 
             "Sweeney", 
             "2017-10-17 15:06:16", 
             "cras@egetvenenatisa.net", 
             "faucibus orci luctus institute", 
             "131-7574 malesuada rd.", 
             "(06) 3076 3992")
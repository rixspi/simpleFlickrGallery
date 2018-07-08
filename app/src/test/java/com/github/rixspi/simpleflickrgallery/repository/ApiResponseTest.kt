package com.github.rixspi.simpleflickrgallery.repository

import com.github.rixspi.simpleflickrgallery.BaseTest
import com.github.rixspi.simpleflickrgallery.repository.net.model.ImagesApiResponse
import com.google.gson.Gson
import junit.framework.Assert.assertTrue
import org.junit.Test


class ApiResponseTest : BaseTest() {


    @Test
    fun `parsing json works`() {
        val gson = Gson()

        val parsed = gson.fromJson<ImagesApiResponse>(json, ImagesApiResponse::class.java)

        assertTrue(parsed.title?.contains("Uploads from everyone") ?: false)
    }


    val json = "{\n" +
            "\t\"title\": \"Uploads from everyone\",\n" +
            "\t\"link\": \"https:\\/\\/www.flickr.com\\/photos\\/\",\n" +
            "\t\"description\": \"\",\n" +
            "\t\"modified\": \"2018-07-06T09:54:14Z\",\n" +
            "\t\"generator\": \"https:\\/\\/www.flickr.com\",\n" +
            "\t\"items\": [{\n" +
            "\t\t\t\"title\": \"Repairing a Wetpour Safety Surface in Shropshire #Wetpour...\",\n" +
            "\t\t\t\"link\": \"https:\\/\\/www.flickr.com\\/photos\\/rubbersurfacinguk\\/28363520497\\/\",\n" +
            "\t\t\t\"media\": {\n" +
            "\t\t\t\t\"m\": \"https:\\/\\/farm1.staticflickr.com\\/923\\/28363520497_27e2532afc_m.jpg\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"date_taken\": \"2018-07-06T02:54:14-08:00\",\n" +
            "\t\t\t\"description\": \" <p><a href=\\\"https:\\/\\/www.flickr.com\\/people\\/rubbersurfacinguk\\/\\\">Playground Rubber Safety Surfacing<\\/a> posted a photo:<\\/p> <p><a href=\\\"https:\\/\\/www.flickr.com\\/photos\\/rubbersurfacinguk\\/28363520497\\/\\\" title=\\\"Repairing a Wetpour Safety Surface in Shropshire #Wetpour...\\\"><img src=\\\"https:\\/\\/farm1.staticflickr.com\\/923\\/28363520497_27e2532afc_m.jpg\\\" width=\\\"240\\\" height=\\\"123\\\" alt=\\\"Repairing a Wetpour Safety Surface in Shropshire #Wetpour...\\\" \\/><\\/a><\\/p> <p>via Playground Rubber Safety Surfacing <a href=\\\"http:\\/\\/rubbersurfacinguk.tumblr.com\\/post\\/175603276216\\\" rel=\\\"nofollow\\\">rubbersurfacinguk.tumblr.com\\/post\\/175603276216<\\/a><\\/p>\",\n" +
            "\t\t\t\"published\": \"2018-07-06T09:54:14Z\",\n" +
            "\t\t\t\"author\": \"nobody@flickr.com (\\\"Playground Rubber Safety Surfacing\\\")\",\n" +
            "\t\t\t\"author_id\": \"148959435@N07\",\n" +
            "\t\t\t\"tags\": \"\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"title\": \"ARIHANT DESIGNER INIFNITY NX FANCY DESIGNER GEORGETTE RAYON KURTIS CATALOG WHOLESALE ONLINE DEALER\",\n" +
            "\t\t\t\"link\": \"https:\\/\\/www.flickr.com\\/photos\\/156917620@N04\\/28363527307\\/\",\n" +
            "\t\t\t\"media\": {\n" +
            "\t\t\t\t\"m\": \"https:\\/\\/farm2.staticflickr.com\\/1768\\/28363527307_4afb90109e_m.jpg\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"date_taken\": \"2018-07-06T02:54:38-08:00\",\n" +
            "\t\t\t\"description\": \" <p><a href=\\\"https:\\/\\/www.flickr.com\\/people\\/156917620@N04\\/\\\">Tushar fabrics<\\/a> posted a photo:<\\/p> <p><a href=\\\"https:\\/\\/www.flickr.com\\/photos\\/156917620@N04\\/28363527307\\/\\\" title=\\\"ARIHANT DESIGNER INIFNITY NX FANCY DESIGNER GEORGETTE RAYON KURTIS CATALOG WHOLESALE ONLINE DEALER\\\"><img src=\\\"https:\\/\\/farm2.staticflickr.com\\/1768\\/28363527307_4afb90109e_m.jpg\\\" width=\\\"170\\\" height=\\\"240\\\" alt=\\\"ARIHANT DESIGNER INIFNITY NX FANCY DESIGNER GEORGETTE RAYON KURTIS CATALOG WHOLESALE ONLINE DEALER\\\" \\/><\\/a><\\/p> <p>On Booking Buy ARIHANT DESIGNER INIFNITY NX FANCY DESIGNER GEORGETTE RAYON KURTIS CATALOG WHOLESALE ONLINE DEALER at Wholesale Price. INR 3180 pcs 4. GEORGETTE Latest catalog INFINITY NX<br \\/> <br \\/> <br \\/> <a href=\\\"http:\\/\\/tusharfabrics.com\\/portfolio\\/arihant-designer-inifnity-nx-fancy-designer-georgette-rayon-kurtis-catalog-wholesale-online-dealer\\/\\\" rel=\\\"nofollow\\\">tusharfabrics.com\\/portfolio\\/arihant-designer-inifnity-nx-...<\\/a><\\/p>\",\n" +
            "\t\t\t\"published\": \"2018-07-06T09:54:38Z\",\n" +
            "\t\t\t\"author\": \"nobody@flickr.com (\\\"Tushar fabrics\\\")\",\n" +
            "\t\t\t\"author_id\": \"156917620@N04\",\n" +
            "\t\t\t\"tags\": \"\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"title\": \"Rubble\",\n" +
            "\t\t\t\"link\": \"https:\\/\\/www.flickr.com\\/photos\\/78423546@N06\\/41423111060\\/\",\n" +
            "\t\t\t\"media\": {\n" +
            "\t\t\t\t\"m\": \"https:\\/\\/farm2.staticflickr.com\\/1784\\/41423111060_aff5fec9a1_m.jpg\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"date_taken\": \"2018-07-06T05:54:27-08:00\",\n" +
            "\t\t\t\"description\": \" <p><a href=\\\"https:\\/\\/www.flickr.com\\/people\\/78423546@N06\\/\\\">GR167<\\/a> posted a photo:<\\/p> <p><a href=\\\"https:\\/\\/www.flickr.com\\/photos\\/78423546@N06\\/41423111060\\/\\\" title=\\\"Rubble\\\"><img src=\\\"https:\\/\\/farm2.staticflickr.com\\/1784\\/41423111060_aff5fec9a1_m.jpg\\\" width=\\\"240\\\" height=\\\"206\\\" alt=\\\"Rubble\\\" \\/><\\/a><\\/p> \",\n" +
            "\t\t\t\"published\": \"2018-07-06T09:54:27Z\",\n" +
            "\t\t\t\"author\": \"nobody@flickr.com (\\\"GR167\\\")\",\n" +
            "\t\t\t\"author_id\": \"78423546@N06\",\n" +
            "\t\t\t\"tags\": \"\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"title\": \"Raza Haxhmi\",\n" +
            "\t\t\t\"link\": \"https:\\/\\/www.flickr.com\\/photos\\/razahaxhmi\\/41423111820\\/\",\n" +
            "\t\t\t\"media\": {\n" +
            "\t\t\t\t\"m\": \"https:\\/\\/farm1.staticflickr.com\\/917\\/41423111820_c9e08cdf7a_m.jpg\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"date_taken\": \"2018-06-16T16:34:46-08:00\",\n" +
            "\t\t\t\"description\": \" <p><a href=\\\"https:\\/\\/www.flickr.com\\/people\\/razahaxhmi\\/\\\">Raza Haxhmi<\\/a> posted a photo:<\\/p> <p><a href=\\\"https:\\/\\/www.flickr.com\\/photos\\/razahaxhmi\\/41423111820\\/\\\" title=\\\"Raza Haxhmi\\\"><img src=\\\"https:\\/\\/farm1.staticflickr.com\\/917\\/41423111820_c9e08cdf7a_m.jpg\\\" width=\\\"120\\\" height=\\\"240\\\" alt=\\\"Raza Haxhmi\\\" \\/><\\/a><\\/p> \",\n" +
            "\t\t\t\"published\": \"2018-07-06T09:54:31Z\",\n" +
            "\t\t\t\"author\": \"nobody@flickr.com (\\\"Raza Haxhmi\\\")\",\n" +
            "\t\t\t\"author_id\": \"131994057@N02\",\n" +
            "\t\t\t\"tags\": \"\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"title\": \"39\",\n" +
            "\t\t\t\"link\": \"https:\\/\\/www.flickr.com\\/photos\\/159156595@N03\\/42328292505\\/\",\n" +
            "\t\t\t\"media\": {\n" +
            "\t\t\t\t\"m\": \"https:\\/\\/farm2.staticflickr.com\\/1822\\/42328292505_0512c931ac_m.jpg\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"date_taken\": \"2018-07-06T02:54:19-08:00\",\n" +
            "\t\t\t\"description\": \" <p><a href=\\\"https:\\/\\/www.flickr.com\\/people\\/159156595@N03\\/\\\">lhoang78<\\/a> posted a photo:<\\/p> <p><a href=\\\"https:\\/\\/www.flickr.com\\/photos\\/159156595@N03\\/42328292505\\/\\\" title=\\\"39\\\"><img src=\\\"https:\\/\\/farm2.staticflickr.com\\/1822\\/42328292505_0512c931ac_m.jpg\\\" width=\\\"169\\\" height=\\\"240\\\" alt=\\\"39\\\" \\/><\\/a><\\/p> <p>by-[ChipVN]-Image-Uploader<\\/p>\",\n" +
            "\t\t\t\"published\": \"2018-07-06T09:54:19Z\",\n" +
            "\t\t\t\"author\": \"nobody@flickr.com (\\\"lhoang78\\\")\",\n" +
            "\t\t\t\"author_id\": \"159156595@N03\",\n" +
            "\t\t\t\"tags\": \"bychipvnimageuploader\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"title\": \"Pusat Makanan Ayer Raja, West Coast Drive\",\n" +
            "\t\t\t\"link\": \"https:\\/\\/www.flickr.com\\/photos\\/145079355@N07\\/42514374644\\/\",\n" +
            "\t\t\t\"media\": {\n" +
            "\t\t\t\t\"m\": \"https:\\/\\/farm2.staticflickr.com\\/1783\\/42514374644_39ebfbf273_m.jpg\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"date_taken\": \"2014-07-05T21:32:32-08:00\",\n" +
            "\t\t\t\"description\": \" <p><a href=\\\"https:\\/\\/www.flickr.com\\/people\\/145079355@N07\\/\\\">Annas Majlam<\\/a> posted a photo:<\\/p> <p><a href=\\\"https:\\/\\/www.flickr.com\\/photos\\/145079355@N07\\/42514374644\\/\\\" title=\\\"Pusat Makanan Ayer Raja, West Coast Drive\\\"><img src=\\\"https:\\/\\/farm2.staticflickr.com\\/1783\\/42514374644_39ebfbf273_m.jpg\\\" width=\\\"240\\\" height=\\\"180\\\" alt=\\\"Pusat Makanan Ayer Raja, West Coast Drive\\\" \\/><\\/a><\\/p> \",\n" +
            "\t\t\t\"published\": \"2018-07-06T09:54:18Z\",\n" +
            "\t\t\t\"author\": \"nobody@flickr.com (\\\"Annas Majlam\\\")\",\n" +
            "\t\t\t\"author_id\": \"145079355@N07\",\n" +
            "\t\t\t\"tags\": \"\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"title\": \"DSC_5272\",\n" +
            "\t\t\t\"link\": \"https:\\/\\/www.flickr.com\\/photos\\/alanstephen\\/42514376754\\/\",\n" +
            "\t\t\t\"media\": {\n" +
            "\t\t\t\t\"m\": \"https:\\/\\/farm2.staticflickr.com\\/1783\\/42514376754_e893063fd5_m.jpg\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"date_taken\": \"2018-06-16T17:46:54-08:00\",\n" +
            "\t\t\t\"description\": \" <p><a href=\\\"https:\\/\\/www.flickr.com\\/people\\/alanstephen\\/\\\">alanstephenphotos<\\/a> posted a photo:<\\/p> <p><a href=\\\"https:\\/\\/www.flickr.com\\/photos\\/alanstephen\\/42514376754\\/\\\" title=\\\"DSC_5272\\\"><img src=\\\"https:\\/\\/farm2.staticflickr.com\\/1783\\/42514376754_e893063fd5_m.jpg\\\" width=\\\"240\\\" height=\\\"160\\\" alt=\\\"DSC_5272\\\" \\/><\\/a><\\/p> \",\n" +
            "\t\t\t\"published\": \"2018-07-06T09:54:27Z\",\n" +
            "\t\t\t\"author\": \"nobody@flickr.com (\\\"alanstephenphotos\\\")\",\n" +
            "\t\t\t\"author_id\": \"156392912@N07\",\n" +
            "\t\t\t\"tags\": \"\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"title\": \"Aerial View of Cullybackey\",\n" +
            "\t\t\t\"link\": \"https:\\/\\/www.flickr.com\\/photos\\/fossie\\/42514377374\\/\",\n" +
            "\t\t\t\"media\": {\n" +
            "\t\t\t\t\"m\": \"https:\\/\\/farm2.staticflickr.com\\/1785\\/42514377374_e0eaee1404_m.jpg\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"date_taken\": \"2017-09-04T19:10:49-08:00\",\n" +
            "\t\t\t\"description\": \" <p><a href=\\\"https:\\/\\/www.flickr.com\\/people\\/fossie\\/\\\">Fossie1<\\/a> posted a photo:<\\/p> <p><a href=\\\"https:\\/\\/www.flickr.com\\/photos\\/fossie\\/42514377374\\/\\\" title=\\\"Aerial View of Cullybackey\\\"><img src=\\\"https:\\/\\/farm2.staticflickr.com\\/1785\\/42514377374_e0eaee1404_m.jpg\\\" width=\\\"240\\\" height=\\\"180\\\" alt=\\\"Aerial View of Cullybackey\\\" \\/><\\/a><\\/p> \",\n" +
            "\t\t\t\"published\": \"2018-07-06T09:54:29Z\",\n" +
            "\t\t\t\"author\": \"nobody@flickr.com (\\\"Fossie1\\\")\",\n" +
            "\t\t\t\"author_id\": \"15606813@N00\",\n" +
            "\t\t\t\"tags\": \"cullybackey northernireland unitedkingdom dji phantom two vision plus drone quadcopter aerial view over gb\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"title\": \"\\u82b1\\u84ee~ \\u96f2\\u5c71\\u6c34\",\n" +
            "\t\t\t\"link\": \"https:\\/\\/www.flickr.com\\/photos\\/106041453@N02\\/42514380254\\/\",\n" +
            "\t\t\t\"media\": {\n" +
            "\t\t\t\t\"m\": \"https:\\/\\/farm2.staticflickr.com\\/1765\\/42514380254_b7d6f599a2_m.jpg\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"date_taken\": \"2018-07-06T11:46:54-08:00\",\n" +
            "\t\t\t\"description\": \" <p><a href=\\\"https:\\/\\/www.flickr.com\\/people\\/106041453@N02\\/\\\">ppbear678<\\/a> posted a photo:<\\/p> <p><a href=\\\"https:\\/\\/www.flickr.com\\/photos\\/106041453@N02\\/42514380254\\/\\\" title=\\\"\\u82b1\\u84ee~ \\u96f2\\u5c71\\u6c34\\\"><img src=\\\"https:\\/\\/farm2.staticflickr.com\\/1765\\/42514380254_b7d6f599a2_m.jpg\\\" width=\\\"135\\\" height=\\\"240\\\" alt=\\\"\\u82b1\\u84ee~ \\u96f2\\u5c71\\u6c34\\\" \\/><\\/a><\\/p> \",\n" +
            "\t\t\t\"published\": \"2018-07-06T09:54:40Z\",\n" +
            "\t\t\t\"author\": \"nobody@flickr.com (\\\"ppbear678\\\")\",\n" +
            "\t\t\t\"author_id\": \"106041453@N02\",\n" +
            "\t\t\t\"tags\": \"\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"title\": \"Acueducto de M\\u00e9rida\",\n" +
            "\t\t\t\"link\": \"https:\\/\\/www.flickr.com\\/photos\\/94233626@N03\\/43183158682\\/\",\n" +
            "\t\t\t\"media\": {\n" +
            "\t\t\t\t\"m\": \"https:\\/\\/farm2.staticflickr.com\\/1825\\/43183158682_0984cddd0f_m.jpg\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"date_taken\": \"2018-07-03T12:29:39-08:00\",\n" +
            "\t\t\t\"description\": \" <p><a href=\\\"https:\\/\\/www.flickr.com\\/people\\/94233626@N03\\/\\\">alir@<\\/a> posted a photo:<\\/p> <p><a href=\\\"https:\\/\\/www.flickr.com\\/photos\\/94233626@N03\\/43183158682\\/\\\" title=\\\"Acueducto de M\\u00e9rida\\\"><img src=\\\"https:\\/\\/farm2.staticflickr.com\\/1825\\/43183158682_0984cddd0f_m.jpg\\\" width=\\\"240\\\" height=\\\"159\\\" alt=\\\"Acueducto de M\\u00e9rida\\\" \\/><\\/a><\\/p> \",\n" +
            "\t\t\t\"published\": \"2018-07-06T09:54:18Z\",\n" +
            "\t\t\t\"author\": \"nobody@flickr.com (\\\"alir@\\\")\",\n" +
            "\t\t\t\"author_id\": \"94233626@N03\",\n" +
            "\t\t\t\"tags\": \"\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"title\": \"DSC_0019\",\n" +
            "\t\t\t\"link\": \"https:\\/\\/www.flickr.com\\/photos\\/165486634@N04\\/43183159342\\/\",\n" +
            "\t\t\t\"media\": {\n" +
            "\t\t\t\t\"m\": \"https:\\/\\/farm1.staticflickr.com\\/924\\/43183159342_aac650bfc0_m.jpg\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"date_taken\": \"2017-12-22T10:50:46-08:00\",\n" +
            "\t\t\t\"description\": \" <p><a href=\\\"https:\\/\\/www.flickr.com\\/people\\/165486634@N04\\/\\\">vanniyarsurenthiran<\\/a> posted a photo:<\\/p> <p><a href=\\\"https:\\/\\/www.flickr.com\\/photos\\/165486634@N04\\/43183159342\\/\\\" title=\\\"DSC_0019\\\"><img src=\\\"https:\\/\\/farm1.staticflickr.com\\/924\\/43183159342_aac650bfc0_m.jpg\\\" width=\\\"135\\\" height=\\\"240\\\" alt=\\\"DSC_0019\\\" \\/><\\/a><\\/p> \",\n" +
            "\t\t\t\"published\": \"2018-07-06T09:54:21Z\",\n" +
            "\t\t\t\"author\": \"nobody@flickr.com (\\\"vanniyarsurenthiran\\\")\",\n" +
            "\t\t\t\"author_id\": \"165486634@N04\",\n" +
            "\t\t\t\"tags\": \"\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"title\": \"2018-07-06_03-11-21\",\n" +
            "\t\t\t\"link\": \"https:\\/\\/www.flickr.com\\/photos\\/152790424@N05\\/43183160962\\/\",\n" +
            "\t\t\t\"media\": {\n" +
            "\t\t\t\t\"m\": \"https:\\/\\/farm1.staticflickr.com\\/923\\/43183160962_a68dd61c31_m.jpg\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"date_taken\": \"2018-07-06T02:41:21-08:00\",\n" +
            "\t\t\t\"description\": \" <p><a href=\\\"https:\\/\\/www.flickr.com\\/people\\/152790424@N05\\/\\\">tranjith1<\\/a> posted a photo:<\\/p> <p><a href=\\\"https:\\/\\/www.flickr.com\\/photos\\/152790424@N05\\/43183160962\\/\\\" title=\\\"2018-07-06_03-11-21\\\"><img src=\\\"https:\\/\\/farm1.staticflickr.com\\/923\\/43183160962_a68dd61c31_m.jpg\\\" width=\\\"240\\\" height=\\\"102\\\" alt=\\\"2018-07-06_03-11-21\\\" \\/><\\/a><\\/p> \",\n" +
            "\t\t\t\"published\": \"2018-07-06T09:54:27Z\",\n" +
            "\t\t\t\"author\": \"nobody@flickr.com (\\\"tranjith1\\\")\",\n" +
            "\t\t\t\"author_id\": \"152790424@N05\",\n" +
            "\t\t\t\"tags\": \"\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"title\": \"Check out my item! MOVEOUT Jul2-8 Amstel Teak Queen Bed Frame Pecan for S\$799! https:\\/\\/sg.carousell.com\\/p\\/178172456 GSS Great Singapore Sale 2018 \\uff01 ======== Link in bio \$10 Discount Gift Voucher for purchase above \$99 \$99 Discount Gift Voucher for purcha\",\n" +
            "\t\t\t\"link\": \"https:\\/\\/www.flickr.com\\/photos\\/161842993@N04\\/43183163242\\/\",\n" +
            "\t\t\t\"media\": {\n" +
            "\t\t\t\t\"m\": \"https:\\/\\/farm1.staticflickr.com\\/841\\/43183163242_b1434bab2c_m.jpg\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"date_taken\": \"2018-07-06T02:54:36-08:00\",\n" +
            "\t\t\t\"description\": \" <p><a href=\\\"https:\\/\\/www.flickr.com\\/people\\/161842993@N04\\/\\\">frenchfurn<\\/a> posted a photo:<\\/p> <p><a href=\\\"https:\\/\\/www.flickr.com\\/photos\\/161842993@N04\\/43183163242\\/\\\" title=\\\"Check out my item! MOVEOUT Jul2-8 Amstel Teak Queen Bed Frame Pecan for S\$799! https:\\/\\/sg.carousell.com\\/p\\/178172456 GSS Great Singapore Sale 2018 \\uff01 ======== Link in bio \$10 Discount Gift Voucher for purchase above \$99 \$99 Discount Gift Voucher for purcha\\\"><img src=\\\"https:\\/\\/farm1.staticflickr.com\\/841\\/43183163242_b1434bab2c_m.jpg\\\" width=\\\"240\\\" height=\\\"240\\\" alt=\\\"Check out my item! MOVEOUT Jul2-8 Amstel Teak Queen Bed Frame Pecan for S\$799! https:\\/\\/sg.carousell.com\\/p\\/178172456 GSS Great Singapore Sale 2018 \\uff01 ======== Link in bio \$10 Discount Gift Voucher for purchase above \$99 \$99 Discount Gift Voucher for purcha\\\" \\/><\\/a><\\/p> \",\n" +
            "\t\t\t\"published\": \"2018-07-06T09:54:36Z\",\n" +
            "\t\t\t\"author\": \"nobody@flickr.com (\\\"frenchfurn\\\")\",\n" +
            "\t\t\t\"author_id\": \"161842993@N04\",\n" +
            "\t\t\t\"tags\": \"instagramapp square squareformat iphoneography uploaded:by=instagram\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"title\": \"New photo added to \\\"Camera Roll\\\"\",\n" +
            "\t\t\t\"link\": \"https:\\/\\/www.flickr.com\\/photos\\/100557040@N04\\/43183164202\\/\",\n" +
            "\t\t\t\"media\": {\n" +
            "\t\t\t\t\"m\": \"https:\\/\\/farm1.staticflickr.com\\/920\\/43183164202_37e52a39b8_m.jpg\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"date_taken\": \"2017-05-19T21:51:33-08:00\",\n" +
            "\t\t\t\"description\": \" <p><a href=\\\"https:\\/\\/www.flickr.com\\/people\\/100557040@N04\\/\\\">vgershteyn<\\/a> posted a photo:<\\/p> <p><a href=\\\"https:\\/\\/www.flickr.com\\/photos\\/100557040@N04\\/43183164202\\/\\\" title=\\\"New photo added to &quot;Camera Roll&quot;\\\"><img src=\\\"https:\\/\\/farm1.staticflickr.com\\/920\\/43183164202_37e52a39b8_m.jpg\\\" width=\\\"168\\\" height=\\\"240\\\" alt=\\\"New photo added to &quot;Camera Roll&quot;\\\" \\/><\\/a><\\/p> \",\n" +
            "\t\t\t\"published\": \"2018-07-06T09:54:39Z\",\n" +
            "\t\t\t\"author\": \"nobody@flickr.com (\\\"vgershteyn\\\")\",\n" +
            "\t\t\t\"author_id\": \"100557040@N04\",\n" +
            "\t\t\t\"tags\": \"ifttt ios photos\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"title\": \"Furniture - Entryway : Striped entryway wall\",\n" +
            "\t\t\t\"link\": \"https:\\/\\/www.flickr.com\\/photos\\/154867010@N02\\/43233022141\\/\",\n" +
            "\t\t\t\"media\": {\n" +
            "\t\t\t\t\"m\": \"https:\\/\\/farm2.staticflickr.com\\/1806\\/43233022141_61fb22c64a_m.jpg\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"date_taken\": \"2018-07-06T02:54:17-08:00\",\n" +
            "\t\t\t\"description\": \" <p><a href=\\\"https:\\/\\/www.flickr.com\\/people\\/154867010@N02\\/\\\">Decor Object<\\/a> posted a photo:<\\/p> <p><a href=\\\"https:\\/\\/www.flickr.com\\/photos\\/154867010@N02\\/43233022141\\/\\\" title=\\\"Furniture - Entryway : Striped entryway wall\\\"><img src=\\\"https:\\/\\/farm2.staticflickr.com\\/1806\\/43233022141_61fb22c64a_m.jpg\\\" width=\\\"160\\\" height=\\\"240\\\" alt=\\\"Furniture - Entryway : Striped entryway wall\\\" \\/><\\/a><\\/p> <p>Home Decor &#8211; Entryway : <br \\/> <br \\/> <br \\/> Striped entryway wall<br \\/> <br \\/> <br \\/> -Read More &#8211; <br \\/> <br \\/> <br \\/> <a href=\\\"https:\\/\\/decorobject.com\\/furniture\\/entryway\\/furniture-entryway-striped-entryway-wall\\/\\\" rel=\\\"nofollow\\\">decorobject.com\\/furniture\\/entryway\\/furniture-entryway-str...<\\/a><\\/p>\",\n" +
            "\t\t\t\"published\": \"2018-07-06T09:54:17Z\",\n" +
            "\t\t\t\"author\": \"nobody@flickr.com (\\\"Decor Object\\\")\",\n" +
            "\t\t\t\"author_id\": \"154867010@N02\",\n" +
            "\t\t\t\"tags\": \"\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"title\": \"Furniture - Entryway : Striped entryway wall\",\n" +
            "\t\t\t\"link\": \"https:\\/\\/www.flickr.com\\/photos\\/164643189@N03\\/43233023411\\/\",\n" +
            "\t\t\t\"media\": {\n" +
            "\t\t\t\t\"m\": \"https:\\/\\/farm2.staticflickr.com\\/1767\\/43233023411_f097385975_m.jpg\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"date_taken\": \"2018-07-06T02:54:22-08:00\",\n" +
            "\t\t\t\"description\": \" <p><a href=\\\"https:\\/\\/www.flickr.com\\/people\\/164643189@N03\\/\\\">Decor Object USA<\\/a> posted a photo:<\\/p> <p><a href=\\\"https:\\/\\/www.flickr.com\\/photos\\/164643189@N03\\/43233023411\\/\\\" title=\\\"Furniture - Entryway : Striped entryway wall\\\"><img src=\\\"https:\\/\\/farm2.staticflickr.com\\/1767\\/43233023411_f097385975_m.jpg\\\" width=\\\"160\\\" height=\\\"240\\\" alt=\\\"Furniture - Entryway : Striped entryway wall\\\" \\/><\\/a><\\/p> <p>Home Decor &#8211; Entryway : <br \\/> <br \\/> <br \\/> Striped entryway wall<br \\/> <br \\/> <br \\/> -Read More &#8211; <br \\/> <br \\/> <br \\/> <a href=\\\"https:\\/\\/decorobject.com\\/furniture\\/entryway\\/furniture-entryway-striped-entryway-wall\\/\\\" rel=\\\"nofollow\\\">decorobject.com\\/furniture\\/entryway\\/furniture-entryway-str...<\\/a><\\/p>\",\n" +
            "\t\t\t\"published\": \"2018-07-06T09:54:22Z\",\n" +
            "\t\t\t\"author\": \"nobody@flickr.com (\\\"Decor Object USA\\\")\",\n" +
            "\t\t\t\"author_id\": \"164643189@N03\",\n" +
            "\t\t\t\"tags\": \"\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"title\": \"#beautiful #beauty #rose #yellow #lady #girl #summer2018 #summer #july\",\n" +
            "\t\t\t\"link\": \"https:\\/\\/www.flickr.com\\/photos\\/53977220@N04\\/43233023831\\/\",\n" +
            "\t\t\t\"media\": {\n" +
            "\t\t\t\t\"m\": \"https:\\/\\/farm1.staticflickr.com\\/835\\/43233023831_cacbe16bff_m.jpg\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"date_taken\": \"2018-07-06T02:06:15-08:00\",\n" +
            "\t\t\t\"description\": \" <p><a href=\\\"https:\\/\\/www.flickr.com\\/people\\/53977220@N04\\/\\\">Yang Nana<\\/a> posted a photo:<\\/p> <p><a href=\\\"https:\\/\\/www.flickr.com\\/photos\\/53977220@N04\\/43233023831\\/\\\" title=\\\"#beautiful #beauty #rose #yellow #lady #girl #summer2018 #summer #july\\\"><img src=\\\"https:\\/\\/farm1.staticflickr.com\\/835\\/43233023831_cacbe16bff_m.jpg\\\" width=\\\"135\\\" height=\\\"240\\\" alt=\\\"#beautiful #beauty #rose #yellow #lady #girl #summer2018 #summer #july\\\" \\/><\\/a><\\/p> \",\n" +
            "\t\t\t\"published\": \"2018-07-06T09:54:24Z\",\n" +
            "\t\t\t\"author\": \"nobody@flickr.com (\\\"Yang Nana\\\")\",\n" +
            "\t\t\t\"author_id\": \"53977220@N04\",\n" +
            "\t\t\t\"tags\": \"beautiful beauty rose yellow lady girl summer2018 summer july\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"title\": \"Drone Photos of Maine\",\n" +
            "\t\t\t\"link\": \"https:\\/\\/www.flickr.com\\/photos\\/153653951@N07\\/43233027541\\/\",\n" +
            "\t\t\t\"media\": {\n" +
            "\t\t\t\t\"m\": \"https:\\/\\/farm1.staticflickr.com\\/843\\/43233027541_e02e4c5a1e_m.jpg\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"date_taken\": \"2018-07-06T12:54:34-08:00\",\n" +
            "\t\t\t\"description\": \" <p><a href=\\\"https:\\/\\/www.flickr.com\\/people\\/153653951@N07\\/\\\">itsmyconverse<\\/a> posted a photo:<\\/p> <p><a href=\\\"https:\\/\\/www.flickr.com\\/photos\\/153653951@N07\\/43233027541\\/\\\" title=\\\"Drone Photos of Maine\\\"><img src=\\\"https:\\/\\/farm1.staticflickr.com\\/843\\/43233027541_e02e4c5a1e_m.jpg\\\" width=\\\"240\\\" height=\\\"180\\\" alt=\\\"Drone Photos of Maine\\\" \\/><\\/a><\\/p> <p>Charlie Toppi<br \\/> <br \\/> <br \\/> (adsbygoogle = window.adsbygoogle || []).push();<br \\/> <br \\/> <br \\/> <br \\/> <br \\/> <br \\/> (adsbygoogle = window.adsbygoogle || []).push();<br \\/> <br \\/> Some photos ive taken over the years with my drone<br \\/> Music By<br \\/> Ringo Star &#8211; Photograph<br \\/> DRONE PHOTOGRAPHY<br \\/> source<br \\/> <br \\/> <br \\/> <a href=\\\"http:\\/\\/dronephotography.site\\/drone-photos-of-maine\\/\\\" rel=\\\"nofollow\\\">dronephotography.site\\/drone-photos-of-maine\\/<\\/a><\\/p>\",\n" +
            "\t\t\t\"published\": \"2018-07-06T09:54:34Z\",\n" +
            "\t\t\t\"author\": \"nobody@flickr.com (\\\"itsmyconverse\\\")\",\n" +
            "\t\t\t\"author_id\": \"153653951@N07\",\n" +
            "\t\t\t\"tags\": \"\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"title\": \"Restaurant Sistar Construction\",\n" +
            "\t\t\t\"link\": \"https:\\/\\/www.flickr.com\\/photos\\/sistarconstruction\\/43233029631\\/\",\n" +
            "\t\t\t\"media\": {\n" +
            "\t\t\t\t\"m\": \"https:\\/\\/farm2.staticflickr.com\\/1787\\/43233029631_8369651da5_m.jpg\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"date_taken\": \"2017-04-07T10:20:49-08:00\",\n" +
            "\t\t\t\"description\": \" <p><a href=\\\"https:\\/\\/www.flickr.com\\/people\\/sistarconstruction\\/\\\">sistar.construction<\\/a> posted a photo:<\\/p> <p><a href=\\\"https:\\/\\/www.flickr.com\\/photos\\/sistarconstruction\\/43233029631\\/\\\" title=\\\"Restaurant Sistar Construction\\\"><img src=\\\"https:\\/\\/farm2.staticflickr.com\\/1787\\/43233029631_8369651da5_m.jpg\\\" width=\\\"240\\\" height=\\\"180\\\" alt=\\\"Restaurant Sistar Construction\\\" \\/><\\/a><\\/p> <p>SISTAR CONSTRUCTION - <a href=\\\"https:\\/\\/www.sistar.fr\\\" rel=\\\"nofollow\\\">www.sistar.fr<\\/a> <br \\/> 2, All\\u00e9e Gambetta 93250 VILLEMOMBLE<br \\/> T\\u00e9l. : 01.48.94.86.03 - Mail : contact@sistar.fr<br \\/> <br \\/> La soci\\u00e9t\\u00e9 SISTAR CONSTRUCTION est une entreprise de b\\u00e2timent tous corps d\\u2019\\u00e9tat, sp\\u00e9cialis\\u00e9e en tous corps d'\\u00e9tat sp\\u00e9cifiquement <br \\/> - G\\u00e9nie climatique et thermique, sanitaire<br \\/> - Peinture et ravalement int\\u00e9rieur ext\\u00e9rieur<br \\/> - Electricit\\u00e9 courant faible<br \\/> - Ma\\u00e7onnerie g\\u00e9n\\u00e9rale <br \\/> Et ce pour les op\\u00e9rations en r\\u00e9novation ou neuf. <br \\/> Nous regroupons toutes les comp\\u00e9tences de conception et de r\\u00e9alisation : Diagnostic technique, \\u00e9tudes, plans, planning, suivi, coordianation et r\\u00e9ception de vos travaux. <br \\/> La soci\\u00e9t\\u00e9 SISTAR CONSTRUCTION est structur\\u00e9 en 3 grands p\\u00f4les : <br \\/> - P\\u00f4le Commercial<br \\/> - P\\u00f4le bureaux d'\\u00e9tudes et d'ing\\u00e9nierie<br \\/> - P\\u00f4le administratif<br \\/> <br \\/> #Vrd #IsolationThermique #RavalementFa\\u00e7ade #PeinturedeFa\\u00e7ade #Plomberie #Chauffage #Ventilation #PeintureInt\\u00e9rieur #PeintureExt\\u00e9rieur #Peinture #Isolation #EntrepriseG\\u00e9n\\u00e9raledeB\\u00e2timent #CouvertureToit #CouvreurToiture #Villemomble #SistarConstruction #SistarFrance #Sistar #CouvreurToitureBacAcier #CouvreurToitureZinc #CouvreurToitureTuile #CouvreurToitureArdoise #CouvreurToitureShingles #R\\u00e9novationToitFibrociment #EntretienToiture #DemoussageToiture #ReparationToiture #ReparationFuiteToiture #Fen\\u00eatredeToitVELUX #Etanch\\u00e9it\\u00e9Toit #Etanch\\u00e9it\\u00e9Terrasse #Etanch\\u00e9it\\u00e9ToitTerrasse #R\\u00e9novationToiture #R\\u00e9novationTerrasse #R\\u00e9novationToitureTerrasse #IsolationToiture #IsolationCombles #IsolationToitureetCombles #Goutti\\u00e8reZinc #Goutti\\u00e8rePVC #CharpenteenBois #TraitementdeCharpente #DevisRapide #ite #Ma\\u00e7onnerie #Mur #Parpaing #ParpaingetBrique #El\\u00e9ctricit\\u00e9G\\u00e9n\\u00e9rale #Placo #Pl\\u00e2trerie #EnduitCiment #EnduitdePeinture #Entreprisedeb\\u00e2timent\\u00e0Paris #AntennisteLivryGargan #Construction #CouvreurLivryGargan #IsolationThermiqueLivryGargan #EntrepriseG\\u00e9n\\u00e9raledeB\\u00e2timentBondy #EntrepriseG\\u00e9n\\u00e9raledeB\\u00e2timentClichysousBois #EntrepriseG\\u00e9n\\u00e9raledeB\\u00e2timentGagny #EntrepriseG\\u00e9n\\u00e9raledeB\\u00e2timentLeRaincy #EntrepriseG\\u00e9n\\u00e9raledeB\\u00e2timentLivryGargan #EntrepriseG\\u00e9n\\u00e9raledeB\\u00e2timentVillemomble #IsolationInt\\u00e9rieure #IsolationExt\\u00e9rieure #RemiseauxNormes #R\\u00e9parationPlomberie #B\\u00e2timent\\u00e0basseconsommation #B\\u00e9tondeChanvre #EntrepriseG\\u00e9n\\u00e9raledeB\\u00e2timentParis #ConstructionDurable #Chantier #LogementsIndividuels #LogementsCollectifs #R\\u00e9novation #Assainissement #IsolationPhonique #FondationSuperficielle #FondationProfonde #FondationSp\\u00e9ciale #SemelleIsol\\u00e9e #SemellesFilantes #FondationsPieux #Puits #PieuxBattus #FondationssurRadier #Longrines #Repriseensousoeuvre #Micropieux #Maconnerie #Etudedesol #Etanch\\u00e9it\\u00e9 #Traitementdelhumidit\\u00e9 #ConstructionsEcologiques #PoseVMCSimpleFlux #PoseVMCDoubleFlux #Terrassement #Am\\u00e9nagementInt\\u00e9rieur #TravauxdePl\\u00e2trerie #MenuiserieInt\\u00e9rieure #MenuiserieExt\\u00e9rieure #CloisonsProtectionauFeu #CloisonsProtectionAcoustique #ChangementdeFen\\u00eatres #ChangementdePortes #PassageauConsuel #R\\u00e9novationEnerg\\u00e9tique #DressingsurMesure #EscaliersenB\\u00e9tonsurMesure #EscaliersenBoissurMesure #PosedeCuisine #PosedeParquet #PosedeCarrelage #construction #entrepriseg\\u00e9n\\u00e9raledeb\\u00e2timent #peintre #peinture #peintureexterieur #peinturefrance #peintureinterieur #peintureint\\u00e9rieur #peintureparis #peinturevillemomble #sistar #sistarconstruction #sistarfrance #sistarparis #sistarpeinture<\\/p>\",\n" +
            "\t\t\t\"published\": \"2018-07-06T09:54:40Z\",\n" +
            "\t\t\t\"author\": \"nobody@flickr.com (\\\"sistar.construction\\\")\",\n" +
            "\t\t\t\"author_id\": \"139861592@N03\",\n" +
            "\t\t\t\"tags\": \"appartement vincennes batiment livrygargan b\\u00e9tonprojet\\u00e9 radier compactagesol design villemomble d\\u00e9coration montreuil maison paris puisard sistarparis leraincy ext\\u00e9rieur peinture feraillage electricit\\u00e9 plomberie construction sistar coffrage pavillonssousbois int\\u00e9rieur sevran plancher entrepriseg\\u00e9n\\u00e9raledeb\\u00e2timent versailles r\\u00e9novation terrassement\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"title\": \"20180704_154607\",\n" +
            "\t\t\t\"link\": \"https:\\/\\/www.flickr.com\\/photos\\/142854982@N03\\/43233029641\\/\",\n" +
            "\t\t\t\"media\": {\n" +
            "\t\t\t\t\"m\": \"https:\\/\\/farm2.staticflickr.com\\/1828\\/43233029641_42df007915_m.jpg\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"date_taken\": \"2018-07-04T15:46:07-08:00\",\n" +
            "\t\t\t\"description\": \" <p><a href=\\\"https:\\/\\/www.flickr.com\\/people\\/142854982@N03\\/\\\">Portela Tom\\u00e9<\\/a> posted a photo:<\\/p> <p><a href=\\\"https:\\/\\/www.flickr.com\\/photos\\/142854982@N03\\/43233029641\\/\\\" title=\\\"20180704_154607\\\"><img src=\\\"https:\\/\\/farm2.staticflickr.com\\/1828\\/43233029641_42df007915_m.jpg\\\" width=\\\"240\\\" height=\\\"135\\\" alt=\\\"20180704_154607\\\" \\/><\\/a><\\/p> \",\n" +
            "\t\t\t\"published\": \"2018-07-06T09:54:40Z\",\n" +
            "\t\t\t\"author\": \"nobody@flickr.com (\\\"Portela Tom\\u00e9\\\")\",\n" +
            "\t\t\t\"author_id\": \"142854982@N03\",\n" +
            "\t\t\t\"tags\": \"\"\n" +
            "\t\t}\n" +
            "\t]\n" +
            "}\n"
}
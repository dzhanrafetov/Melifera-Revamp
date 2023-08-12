package com.dzhanrafetov.melifera.dto.image_dtos;

public class ImageDto {

        private Long image_id;
        private String name;
        private String url;


        public ImageDto() {
        }

    public ImageDto(Long image_id,String name, String url) {
            this.name=name;
        this.image_id = image_id;
        this.url = url;
    }



    public ImageDto(String url) {

            this.url = url;
        }


        public String getUrl() {
            return url;
        }

        public Long getImage_id() {
            return image_id;
        }

    public String getName() {
        return name;
    }


}


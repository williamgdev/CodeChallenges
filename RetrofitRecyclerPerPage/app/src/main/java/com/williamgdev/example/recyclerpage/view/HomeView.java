package com.williamgdev.example.recyclerpage.view;

import com.williamgdev.example.recyclerpage.dto.CatResponse;

import java.util.List;

public interface HomeView extends BaseView{
    void setCats(List<CatResponse> catsResponseList);
}

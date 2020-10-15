# An Android App Project - NaylaLabs Firm
> This project maked by owner, for internship at the NaylaLabs firm on a week.

## Table of contents
* [General info](#general-info)
* [Screenshots](#screenshots)
* [Features](#features)
* [Status](#status)
* [Contact](#contact)

## General info
This project is an example app in android studio. Also including Chip Bottom Nav, ShapeOfView library. Recyclerview and NestedScrolView used together.
For the Navigation, fragment used.

## Screenshots
![Example video](https://s8.gifyu.com/images/20201016-012455.gif)



## Code Examples
Show examples of usage:


    @Override
    public void onStart() {
        super.onStart();

        final RecyclerView recyclerView =
                getActivity().findViewById(R.id.rv_fragment_profile_recent_courses);

        final NestedScrollView nestedScrollView =
                getActivity().findViewById(R.id.nscoll_view_fragment_profile_parent);



        RecentCoursesRvAdapter recentCoursesRvAdapter =
                new RecentCoursesRvAdapter(loadData(),
                        new RecentCoursesRvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecentCours recentCours, int position) {

                ( (RecentCoursesRvAdapter)(recyclerView.getAdapter()) ).getCertificate(position);
            }
        });

        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setAdapter(recentCoursesRvAdapter);

        //TODO burada iyileştirme yapılacak ...
        /*ViewGroup.LayoutParams params = nestedScrollView.getLayoutParams();
        params.height = 400;
        nestedScrollView.setLayoutParams(params);*/

        scrollUp(nestedScrollView);


    }

## Features
List of features ready and TODOs for future development
* Inbox message listing
* Recent Courses listing

To-do list:
* Size up text and layout of badges in botton nav.
* Login with gmail

## Status
Project is: _in progress_


## Contact
Created by [karazeynal9@gmail.com](mailto:karazeynal9@gmail.com) - feel free to contact me!

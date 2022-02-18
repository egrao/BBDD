package com.example.bbdd;

public class Comment {
    int id;
    String name;
    String comment;


    public Comment(int _id,String _name,String _comment){
        id=_id;
        name=_name;
        comment=_comment;
    }


    @Override
    public String toString() {
        return name;
    }


    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getComment(){
        return comment;
    }


}

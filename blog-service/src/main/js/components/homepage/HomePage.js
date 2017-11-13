import React from "react";
import PostForm from "../post/PostForm"

class HomePage extends React.Component {

    constructor(props) {
        super(props);
    }

    render() {
        return (
            <PostForm/>
        )
    }
}
export default HomePage;
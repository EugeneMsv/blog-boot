import React from "react";
import FontIcon from "material-ui/FontIcon";
import {BottomNavigation, BottomNavigationItem} from "material-ui/BottomNavigation";
import Paper from "material-ui/Paper";

const recentsIcon = <FontIcon className="material-icons">Последнее</FontIcon>;
const favoritesIcon = <FontIcon className="material-icons">Актуальное</FontIcon>;


class Footer extends React.Component {
    state = {
        selectedIndex: 0,
    };

    select = (index) => this.setState({selectedIndex: index});

    render() {
        return (
            <Paper zDepth={1}>
                <BottomNavigation selectedIndex={this.state.selectedIndex}>
                    <BottomNavigationItem
                        label="Recents"
                        icon={recentsIcon}
                        onClick={() => this.select(0)}
                    />
                    <BottomNavigationItem
                        label="Favorites"
                        icon={favoritesIcon}
                        onClick={() => this.select(1)}
                    />
                </BottomNavigation>
            </Paper>
        );
    }
}

export default Footer;


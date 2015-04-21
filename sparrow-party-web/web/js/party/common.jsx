function isIE () {
	var myNav = navigator.userAgent.toLowerCase();
	return (myNav.indexOf('msie') != -1) ? parseInt(myNav.split('msie')[1]) : false;
}

// react bootstrap define
var Alert = ReactBootstrap.Alert;
var Button = ReactBootstrap.Button;
var ButtonGroup = ReactBootstrap.ButtonGroup;
var ButtonToolbar = ReactBootstrap.ButtonToolbar;
var CollapsableNav = ReactBootstrap.CollapsableNav;
var DropdownButton = ReactBootstrap.DropdownButton;
var Glyphicon = ReactBootstrap.Glyphicon;
var Input = ReactBootstrap.Input;
var Jumbotron = ReactBootstrap.Jumbotron;
var Label = ReactBootstrap.Label;
var MenuItem = ReactBootstrap.MenuItem;
var Modal = ReactBootstrap.Modal;
var Nav = ReactBootstrap.Nav;
var Navbar = ReactBootstrap.Navbar;
var NavItem = ReactBootstrap.NavItem;
var OverlayTrigger = ReactBootstrap.OverlayTrigger;
var Panel = ReactBootstrap.Panel;
var PanelGroup = ReactBootstrap.PanelGroup;
var Popover = ReactBootstrap.Popover;
var Tooltip = ReactBootstrap.Tooltip;

// menu object
var navbar = (
	<Navbar brand="Sparrow-Party" toggleNavKey={0} fixedTop={true}>
		<CollapsableNav eventKey={0}>
	  		<Nav navbar>
				<NavItem href="party-index.html">Home</NavItem>
				<DropdownButton title="Party">
					<MenuItem href="create-party.html">Create Party</MenuItem>
					<MenuItem divider />
					<MenuItem>Find Party</MenuItem>
				</DropdownButton>
			</Nav>
			<form className="navbar-form navbar-right" role="search">
				<Input type="text" placeholder="Search Party..." buttonAfter={<Button><Glyphicon glyph="search" /></Button>} />
		    </form>
		</CollapsableNav>
	</Navbar>
);

React.render(navbar, document.getElementById("nav"));

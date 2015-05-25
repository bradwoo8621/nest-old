/**
 * modal confirm dialog
 */
var NConfirm = React.createClass({
    propTypes: {
        css: React.PropTypes.string,
        zIndex: React.PropTypes.number
    },
    getInitialState: function () {
        return {
            visible: false,
            title: null,
            messages: null,
            onConfirm: null
        };
    },
    /**
     * set z-index
     */
    setZIndex: function () {
        if (this.props.zIndex != undefined) {
            var div = $(React.findDOMNode(this.refs.body)).closest(".modal");
            if (div.length > 0) {
                div.css({"z-index": this.props.zIndex * 1 + 1});
                div.prev().css({"z-index": this.props.zIndex});
            }
        }
    },
    /**
     * did update
     * @param prevProps
     * @param prevState
     */
    componentDidUpdate: function (prevProps, prevState) {
        this.setZIndex();
    },
    /**
     * did mount
     */
    componentDidMount: function () {
        this.setZIndex();
    },
    /**
     * render
     * @returns {*}
     */
    render: function () {
        if (!this.state.visible) {
            return null;
        }

        var messages = this.state.messages;
        if (!Array.isArray(messages)) {
            messages = [this.state.messages];
        }
        return (<Modal className={this.props.css} bsStyle="danger" title={this.state.title}
                       onRequestHide={this.hide} backdrop="static">
            <div className="modal-body" ref="body">
                {messages.map(function (element) {
                    return <h5>{element}</h5>;
                })}
            </div>
            <div className="modal-footer">
                <Button bsStyle="danger" onClick={this.onConfirmClicked}>OK</Button>
                <Button onClick={this.hide}>Cancel</Button>
            </div>
        </Modal>);
    },
    /**
     * hide dialog
     */
    hide: function () {
        this.setState({visible: false});
    },
    /**
     * on confirm clicked
     */
    onConfirmClicked: function () {
        if (this.state.onConfirm) {
            this.state.onConfirm();
        }
        this.hide();
    },
    /**
     * show dialog
     * @param title
     * @param messages
     * @param onConfirm
     */
    show: function (title, messages, onConfirm) {
        var state = {
            visible: true,
            title: title,
            messages: messages,
            onConfirm: onConfirm
        };
        this.setState(state);
    }
});
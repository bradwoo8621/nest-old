/**
 * modal confirm dialog. the z-index of layer is 1040 and 1050, so if want to change, set the "zIndex" property.
 */
var ModalConfirmDialog = React.createClass({
    /**
     * @override
     */
    propTypes: {
        className: React.PropTypes.string,
        zIndex: React.PropTypes.number
    },
    /**
     * override react method
     * @returns {*}
     * @override
     */
    getInitialState: function () {
        return {
            visible: false,
            title: null,
            messages: null,
            callback: null
        };
    },
    /**
     * override react method
     * @param prevProps
     * @param prevState
     * @override
     */
    componentDidUpdate: function (prevProps, prevState) {
        this.setZIndex();
    },
    /**
     * override react method
     * @override
     */
    componentDidMount: function () {
        this.setZIndex();
    },
    /**
     * override react method
     * @returns {XML}
     * @override
     */
    render: function () {
        if (!this.state.visible) {
            return null;
        }

        var messages = this.state.messages;
        if (!this.state.messages.length) {
            messages = [this.state.messages];
        }
        return (<Modal className={this.props.className} bsStyle="danger" title={this.state.title}
                       onRequestHide={this.hide} backdrop="static">
            <div className="modal-body" ref="body">
                {messages.map(function (element) {
                    return <h5>{element}</h5>;
                })}
            </div>
            <div className="modal-footer">
                <Button bsStyle="danger" onClick={this.confirmClicked}>OK</Button>
                <Button onClick={this.hide}>Cancel</Button>
            </div>
        </Modal>);
    },
    /**
     * callback when click confirm button
     */
    confirmClicked: function () {
        if (this.state.callback) {
            if (this.state.callbackParameters !== undefined) {
                this.state.callback(this.state.callbackParameters);
            } else {
                this.state.callback();
            }
        }
    },
    /**
     * show dialog
     * @param title
     * @param messages
     * @param confirmCallback
     * @param callbackParameters
     */
    show: function (title, messages, confirmCallback, callbackParameters) {
        var state = {
            visible: true,
            title: title,
            messages: messages,
            callback: confirmCallback
        };
        if (callbackParameters !== undefined) {
            state.callbackParameters = callbackParameters;
        }
        this.setState(state);
    },
    /**
     * hide dialog
     */
    hide: function () {
        this.setState({visible: false});
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
    }
});

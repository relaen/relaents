
import {DeviceModel} from './devicemodel'
import {DeviceLoc} from './deviceloc'
import {IotServer} from './iotserver'
import {ChannelCfg} from './channelcfg'
import {DeviceLog} from './devicelog'
import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator';
import { BaseEntity } from '../../core/entity';
import { EFkConstraint } from '../../core/entitydefine';

@Entity("t_device",'tunnel')
export class Device extends BaseEntity{
	@Id()
	@Column({
		name:'devide_id',
		type:'int',
		nullable:false
	})
	devideId:number;

	@ManyToOne({entity:Device,lazyFetch:true})
	@JoinColumn({name:'device_model_id',refName:'device_model_id'})
	deviceModel:DeviceModel;

	@ManyToOne({entity:Device,lazyFetch:true})
	@JoinColumn({name:'device_loc_id',refName:'device_loc_id'})
	deviceLoc:DeviceLoc;

	@ManyToOne({entity:Device,lazyFetch:true})
	@JoinColumn({name:'iot_server_id',refName:'iot_server_id'})
	iotServer:IotServer;

	@Column({
		name:'device_no',
		type:'string',
		nullable:true
	})
	deviceNo:string;

	@Column({
		name:'device_code',
		type:'string',
		nullable:true
	})
	deviceCode:string;

	@Column({
		name:'children_num',
		type:'int',
		nullable:true
	})
	childrenNum:number;

	@Column({
		name:'start_time',
		type:'int',
		nullable:true
	})
	startTime:number;

	@Column({
		name:'end_time',
		type:'int',
		nullable:true
	})
	endTime:number;

	@Column({
		name:'enabled',
		type:'int',
		nullable:true
	})
	enabled:number;

	@Column({
		name:'stop_reason',
		type:'string',
		nullable:true
	})
	stopReason:string;

	@Column({
		name:'lane_no',
		type:'int',
		nullable:true
	})
	laneNo:number;

	@Column({
		name:'collect_freq',
		type:'int',
		nullable:true
	})
	collectFreq:number;

	@OneToMany({entity:'ChannelCfg',onDelete:EFkConstraint.CASCADE,onUpdate:EFkConstraint.CASCADE,mappedBy:'deviceForChildDevideId',lazyFetch:true})
	channelCfgForChildDevideIds:Array<ChannelCfg>;

	@OneToMany({entity:'ChannelCfg',onDelete:EFkConstraint.CASCADE,onUpdate:EFkConstraint.CASCADE,mappedBy:'deviceForParentDevideId',lazyFetch:true})
	channelCfgForParentDevideIds:Array<ChannelCfg>;

	@OneToMany({entity:'DeviceLog',onDelete:EFkConstraint.CASCADE,onUpdate:EFkConstraint.CASCADE,mappedBy:'device',lazyFetch:true})
	deviceLogs:Array<DeviceLog>;

}
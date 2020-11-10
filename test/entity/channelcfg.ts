
import {Device} from './device'
import {DeviceChannel} from './devicechannel'
import {ChannelLog} from './channellog'
import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator';
import { BaseEntity } from '../../core/entity';
import { EFkConstraint } from '../../core/entitydefine';

@Entity("t_channel_cfg",'tunnel')
export class ChannelCfg extends BaseEntity{
	@Id()
	@Column({
		name:'channel_cfg_id',
		type:'int',
		nullable:false
	})
	channelCfgId:number;

	@ManyToOne({entity:ChannelCfg,lazyFetch:true})
	@JoinColumn({name:'child_devide_id',refName:'child_devide_id'})
	deviceForChildDevideId:Device;

	@ManyToOne({entity:ChannelCfg,lazyFetch:true})
	@JoinColumn({name:'parent_devide_id',refName:'parent_devide_id'})
	deviceForParentDevideId:Device;

	@ManyToOne({entity:ChannelCfg,lazyFetch:true})
	@JoinColumn({name:'device_channel_id',refName:'device_channel_id'})
	deviceChannel:DeviceChannel;

	@Column({
		name:'parent_channel_no',
		type:'int',
		nullable:true
	})
	parentChannelNo:number;

	@Column({
		name:'child_channel_no',
		type:'int',
		nullable:true
	})
	childChannelNo:number;

	@OneToMany({entity:'ChannelLog',onDelete:EFkConstraint.CASCADE,onUpdate:EFkConstraint.CASCADE,mappedBy:'channelCfg',lazyFetch:true})
	channelLogs:Array<ChannelLog>;

}
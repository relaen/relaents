import {Tunnel} from './tunnel'
import {Device} from './device'
import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator';
import { BaseEntity } from '../../core/entity';
import { EFkConstraint } from '../../core/entitydefine';

@Entity("t_device_loc",'tunnel')
export class DeviceLoc extends BaseEntity{
	@Id()
	@Column({
		name:'device_loc_id',
		type:'int',
		nullable:false
	})
	deviceLocId:number;

	@ManyToOne({entity:DeviceLoc,lazyFetch:true})
	@JoinColumn({name:'tunnel_id',refName:'tunnel_id'})
	tunnel:Tunnel;

	@Column({
		name:'loc_no',
		type:'int',
		nullable:true
	})
	locNo:number;

	@Column({
		name:'x',
		type:'double',
		nullable:true
	})
	x:number;

	@Column({
		name:'y',
		type:'double',
		nullable:true
	})
	y:number;

	@Column({
		name:'z',
		type:'double',
		nullable:true
	})
	z:number;

	@Column({
		name:'loc_desc',
		type:'string',
		nullable:true
	})
	locDesc:string;

	@OneToMany({entity:'Device',onDelete:EFkConstraint.SETNULL,onUpdate:EFkConstraint.CASCADE,mappedBy:'deviceLoc',lazyFetch:true})
	devices:Array<Device>;

}